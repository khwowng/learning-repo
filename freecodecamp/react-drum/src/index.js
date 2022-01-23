import React from 'react';
import ReactDOM from 'react-dom';
import "bootstrap/dist/css/bootstrap.min.css";
import './index.css';

const sounds = [
  {
    key: "Q",
    id: "Heater-1",
    url: "https://s3.amazonaws.com/freecodecamp/drums/Heater-1.mp3",
  },
  {
    key: "W",
    id: "Heater-2",
    url: "https://s3.amazonaws.com/freecodecamp/drums/Heater-2.mp3",
  },
  {
    key: "E",
    id: "Heater-3",
    url: "https://s3.amazonaws.com/freecodecamp/drums/Heater-3.mp3",
  },
  {
    key: "A",
    id: "Heater-4",
    url: "https://s3.amazonaws.com/freecodecamp/drums/Heater-4_1.mp3",
  },
  {
    key: "S",
    id: "Clap",
    url: "https://s3.amazonaws.com/freecodecamp/drums/Heater-6.mp3",
  },
  {
    key: "D",
    id: "Open-HH",
    url: "https://s3.amazonaws.com/freecodecamp/drums/Dsc_Oh.mp3",
  },
  {
    key: "Z",
    id: "Kick-n'-Hat",
    url: "https://s3.amazonaws.com/freecodecamp/drums/Kick_n_Hat.mp3",
  },
  {
    key: "X",
    id: "Kick",
    url: "https://s3.amazonaws.com/freecodecamp/drums/RP4_KICK_1.mp3",
  },
  {
    key: "C",
    id: "Closed-HH",
    url: "https://s3.amazonaws.com/freecodecamp/drums/Cev_H2.mp3",
  },
];

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      text: "Drum Machine App",
    };
  }
  render() {
    return (
      <div id="drum-machine">
        <div id="display" className="display bg-light">
          <h1 className="text-center text-warning">{this.state.text}</h1>
          {sounds.map((sound, idx) => (
            <Box text={sound.key} key={idx} audio={sound.url} />
          ))}
        </div>
      </div>
    );
  }
}

class Box extends React.Component {
  constructor(props) {
    super(props);
    this.audio = React.createRef();
    window.document.addEventListener("keydown", (e) => {
      if (e.key.toUpperCase() === props.text) {
        this.audio.current.play();
        const id = this.audio.current.id;
        const parent = this.audio.current.parentNode.parentNode;
        parent.querySelector("h1").innerText = `${id} is playing`;
      }
    });
  }
  playSound = () => {
    this.audio.current.play();
    const id = this.audio.current.id;
    const parent = this.audio.current.parentNode.parentNode;
    parent.querySelector("h1").innerText = `${id} is playing`;
  };
  render() {
    const { text, audio } = this.props;
    return (
      <div
        className="drum-pad btn btn-warning text-white"
        onClick={this.playSound}
        id={this.props.text}
      >
        {this.props.text}
        <audio
          ref={this.audio}
          src={audio}
          className="clip"
          id={this.props.text}
        />
      </div>
    );
  }
}

ReactDOM.render(<App />, document.getElementById("root"));
