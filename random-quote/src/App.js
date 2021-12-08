/* 
1. fetch api to get data
2. generate random number for random quote
3. display quote
*/

import './App.css';
import React from 'react'
import "bootstrap/dist/css/bootstrap.min.css";

const API =
  "https://gist.githubusercontent.com/camperbot/5a022b72e96c4c9585c32bf6a75f62d9/raw/e3c6895ce42069f0ee7e991229064f167fe8ccdc/quotes.json";

class App extends React.Component {
  state = {
    quotes: [
      {
        quote:
          "It does not matter how slowly you go as long as you do not stop.",
        author: "Confucius",
      },
    ],
    index: 0,
  };
  // call an API endpoint to retrieve data
  componentDidMount() {
    fetch(API) 
      .then((res) => res.json()) 
      .then((res) => {
        this.setState(
          {
            quotes: res.quotes, // whole quotes database as an object
          },
          this.getRandomNumber // get a random number for an index
        );
      });
  }

  getRandomNumber = () => {
    const { quotes } = this.state;
    if (quotes.length > 0) {
      const index = Math.floor(Math.random() * quotes.length);
      this.setState({
        index,
      });
    }
  };

  render() {
    const { quotes, index } = this.state;
    const quote = quotes[index];
    const tweetURL = `https://twitter.com/intent/tweet?text=${quote.quote}-${quote.author}`;
    return (
      <div id="wrapper" className="flex bg-light">
        <div id="quote-box" className="col-6 p-4 rounded bg-white">
          {
            // check if we have quote
            quote && (
              <p>
                <h4 id="text">
                  <i className="fas fa-quote-right"></i>
                  {quote.quote}
                </h4>
                <cite id="author" className="d-block text-right">
                  {quote.author}
                </cite>
              </p>
            )
          }
          <div className="d-flex justify-content-between">
            <a
              id="tweet-quote"
              href={tweetURL}
              target="_blank"
              className="btn btn-primary"
            >
              <i className="fab fa-twitter-square"></i>
              Tweet
            </a>
            <button
              id="new-quote"
              className="btn btn-primary"
              onClick={this.getRandomNumber} // trigger new random number for new quote
            >
              New Quote
            </button>
          </div>
        </div>
      </div>
    );
  }
}

export default App;
