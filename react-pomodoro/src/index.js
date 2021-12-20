import React from 'react';
import ReactDOM from 'react-dom';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import "bootstrap/dist/css/bootstrap.min.css";
import { faMinus, faPlus, faPlay, faSync } from "@fortawesome/free-solid-svg-icons";

import "./index.css";

class App extends React.Component {
  constructor(props) {
    super(props);
    this.loop = undefined;
  }

  state = {
    breakCount: 5,
    sessionCount: 25,
    clockCount: 25 * 60,
    isPlaying: false,
    currentTimer: "Session",
  };

  componentWillUnmount() {
    clearInterval(this.loop);
  }

  convertToTime(count) {
    let minutes = Math.floor(count / 60);
    let seconds = count % 60;

    minutes = minutes < 10 ? "0" + minutes : minutes;
    seconds = seconds < 10 ? "0" + seconds : seconds;

    return `${minutes}:${seconds}`;
  }

  handlePlayPause = () => {
    const { isPlaying } = this.state;
    if (isPlaying) {
      clearInterval(this.loop);
      this.setState({
        isPlaying: false,
      });
    } else {
      this.setState({
        isPlaying: true,
      });
      this.loop = setInterval(() => {
        const { breakCount, sessionCount, clockCount, currentTimer } =
          this.state;
        if (clockCount === 0) {
          this.audioBeep.play();
          this.setState({
            currentTimer: currentTimer === "Session" ? "Break" : "Session",
            clockCount:
              currentTimer === "Session" ? breakCount * 60 : sessionCount * 60,
          });
        } else {
          this.setState({
            clockCount: clockCount - 1,
          });
        }
      }, 1000);
    }
  };

  handleLengthChange = (count, timerType) => {
    const { sessionCount, breakCount, isPlaying, currentTimer } = this.state;

    let newCount;

    if (timerType === "session") {
      newCount = sessionCount + count;
    } else {
      newCount = breakCount + count;
    }

    if (newCount > 0 && newCount < 61 && !isPlaying) {
      this.setState({
        [`${timerType}Count`]: newCount,
      });

      if (currentTimer.toLowerCase() === timerType) {
        this.setState({
          clockCount: newCount * 60,
        });
      }
    }
  };

  handleReset = () => {
    this.setState({
      breakCount: 5,
      sessionCount: 25,
      clockCount: 25 * 60,
      isPlaying: false,
      currentTimer: "Session",
    });
    clearInterval(this.loop);
    this.audioBeep.pause();
    this.audioBeep.currentTime = 0;
  };

  render() {
    const { breakCount, sessionCount, clockCount, currentTimer, isPlaying } =
      this.state;

    const breakProps = {
      title: "Break",
      count: this.state.breakCount,
      handleDec: () => this.handleLengthChange(-1, "break"),
      handleInc: () => this.handleLengthChange(1, "break"),
    };
    const sessionProps = {
      title: "Session",
      count: this.state.sessionCount,
      handleDec: () => this.handleLengthChange(-1, "session"),
      handleInc: () => this.handleLengthChange(1, "session"),
    };

    return (
      <div>
        <div className="flex">
          <SetTimer {...breakProps} />
          <SetTimer {...sessionProps} />
        </div>
        <div className="clock-container">
          <h1 id="timer-label">{currentTimer}</h1>
          <span id="time-left">{this.convertToTime(clockCount)}</span>
          <div className="flex btn-wrapper">
            <button
              className="btn"
              id="start_stop"
              onClick={this.handlePlayPause}
            >
              <FontAwesomeIcon
                icon={faPlay}
              />
            </button>
            <button id="reset" className="btn" onClick={this.handleReset}>
              <FontAwesomeIcon icon={faSync} />
            </button>
          </div>
        </div>
        <audio
          id="beep"
          preload="auto"
          ref={(audio) => {
            this.audioBeep = audio;
          }}
          src="https://raw.githubusercontent.com/freeCodeCamp/cdn/master/build/testable-projects-fcc/audio/BeepSound.wav"
        />
      </div>
    );
  }
}

const SetTimer = (props) => {
  const id = props.title.toLowerCase();
  return (
    <div className="timer-container">
      <h2 id={`${id}-label`}>{props.title} Length</h2>
      <div className="flex actions-wrapper">
        <button
          id={`${id}-decrement`}
          onClick={props.handleDec}
          className="btn btn-light btn-sm"
        >
          <FontAwesomeIcon icon={faMinus} />
        </button>

        <span id={`${id}-length`}>{props.count}</span>

        <button
          id={`${id}-increment`}
          onClick={props.handleInc}
          className="btn btn-light btn-sm"
        >
          <FontAwesomeIcon icon={faPlus} />
        </button>
      </div>
    </div>
  );
};

ReactDOM.render(<App />, document.getElementById("root"));
