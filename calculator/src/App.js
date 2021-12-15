import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import React from "react";

const nums = [7, 8, 9, 4, 5, 6, 1, 2, 3, 0];
const ops = ["/", "*", "-", "+"];
const ids = {
  7: "seven",
  8: "eight",
  9: "nine",
  4: "four",
  5: "five",
  6: "six",
  1: "one",
  2: "two",
  3: "three",
  0: "zero",
  "/": "divide",
  "*": "multiply",
  "-": "subtract",
  "+": "add",
};

class App extends React.Component {
  state = {
    lastPressed: undefined,
    operation: undefined,
    calc: "0",
  };
  handleClick = (e) => {
    const { calc, lastPressed } = this.state;
    const { innerText } = e.target;
    switch (innerText) {
      case "AC": {
        this.setState({
          calc: "0",
        });
        break;
      }
      case "=": {
        const evaluated = eval(calc);
        this.setState({ calc: evaluated });
        break;
      }
      case ".": {
        const splitted = calc.split(/[\+\-\*\/]/);
        const last = splitted.slice(-1)[0];
        console.log(splitted);
        if (!last.includes(".")) {
          this.setState({
            calc: calc + ".",
          });
        }
        break;
      }
      default: {
        // current button is operator
        if (ops.includes(innerText)) {
          // last one is operator and not minus
          if (ops.includes(lastPressed) && innerText !== "-") {
            const splitted = calc.split("").reverse();
            const isNumber = (char) => nums.includes(+char);
            const idx = splitted.findIndex(isNumber);
            e = calc.slice(0, idx + 1) + ` ${innerText} `;
          } // last one is not operator or is operator but minus
          else {
            e = `${calc} ${innerText} `;
          }
        } else {
          e = calc === "0" ? innerText : calc + innerText;
        }
        this.setState({
          calc: e,
        });
      }
    }
    this.setState({
      lastPressed: innerText,
    });
  };

  render() {
    const { calc } = this.state;
    return (
      <div className="calculator">
        <div id="display" className="display">
          {calc}
        </div>
        <div className="nums-container">
          <button className="light-grey" id="clear" onClick={this.handleClick}>
            AC
          </button>
          <button className="light-grey">CE</button>
          <button className="light-grey">%</button>
          {nums.map((num) => (
            <button
              className={`dark-grey ${num === 0 && "big-h"}`}
              key={num}
              onClick={this.handleClick}
              id={ids[num]}
            >
              {num}
            </button>
          ))}
          <button
            id="decimal"
            className="light-grey"
            onClick={this.handleClick}
          >
            .
          </button>
        </div>
        <div className="ops-container">
          {ops.map((op) => (
            <button
              className="orange"
              key={op}
              onClick={this.handleClick}
              id={ids[op]}
            >
              {op}
            </button>
          ))}
          <button id="equals" className="orange" onClick={this.handleClick}>
            =
          </button>
        </div>
      </div>
    );
  }
}

export default App;
