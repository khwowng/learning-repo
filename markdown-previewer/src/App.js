// use marked package
// type text and render marked to another div

import React from "react";
import { marked } from "marked";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

marked.setOptions({
  breaks: true,
});
const renderMarkdown = new marked.Renderer();

// use `` instead of normal quote
const placeholder = `# Welcome to my React Markdown Previewer!

## This is a sub-heading...
### And here's some other cool stuff:

Heres some code, \`<div></div>\`, between 2 backticks.

\`\`\`
// this is multi-line code:

function anotherExample(firstLine, lastLine) {
  if (firstLine == '\`\`\`' && lastLine == '\`\`\`') {
    return multiLineCode;
  }
}
\`\`\`

You can also make text **bold**... whoa!
Or _italic_.
Or... wait for it... **_both!_**
And feel free to go crazy ~~crossing stuff out~~.

There's also [links](https://www.freecodecamp.org), and
> Block Quotes!

And if you want to get really crazy, even tables:

Wild Header | Crazy Header | Another Header?
------------ | ------------- | -------------
Your content can | be here, and it | can be here....
And here. | Okay. | I think we get it.

- And of course there are lists.
  - Some are bulleted.
     - With different indentation levels.
        - That look like this.


1. And there are numbered lists too.
1. Use just 1s if you want!
1. And last but not least, let's not forget embedded images:

![freeCodeCamp Logo](https://cdn.freecodecamp.org/testable-projects-fcc/images/fcc_secondary.svg)`;
class App extends React.Component {

  state = {
    text: placeholder,
  };

  handleChange = (e) => {
    this.setState({
      text: e.target.value, // update input
    });
  };

  render() {
    const { text } = this.state;

    return (
      <div>
        <h3 className="text-center m-4"> Convert your Markdown </h3>
        <div className="row">
          <div className="col-6">
            <h5 className="text-center"> Enter your markdown: </h5>
            <textarea
              id="editor"
              className="form-control preview p-2"
              // update text input as typed
              value={text}
              onChange={this.handleChange}
            />
          </div>
          <div className="col-6">
            <h5 className="text-center"> See the result: </h5>
            <div
              className="preview rounded p-2 form-control"
              id="preview"
              // render markdown output to
              dangerouslySetInnerHTML={{
                __html: marked(this.state.text, { renderer: renderMarkdown }),
              }}
            ></div>
          </div>
        </div>
      </div>
    );
  }
}
export default App;
