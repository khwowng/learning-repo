import { useState, useEffect } from "react";
import React, { Component } from "react";
import axios from "axios";
import { Table, Form, Button } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";

export default function App() {
  const [usersList, setUsersList] = useState([]);
  const [name, setName] = useState("");
  const [age, setAge] = useState(0);
  const [username, setUsername] = useState("");

  useEffect(() => {
    axios.get("http://localhost:5000/users").then((res) => {
      setUsersList(res.data);
    });
  }, []);

  const createUser = (e) => {
    axios
      .post("http://localhost:5000/users", {
        name,
        age,
        username,
      })
      .then((res) => {
        setUsersList([
          ...usersList,
          {
            name,
            age,
            username,
          },
        ]);
      });
  };

  return (
    <div className="container m-5">
      <Form className="usersCreated">
        <h2> Create User </h2>
        <Form.Control
          type="text"
          placeholder="Name..."
          onChange={(e) => {
            setName(e.target.value);
          }}
        />
        <Form.Control
          type="number"
          placeholder="Age..."
          onChange={(e) => {
            setAge(e.target.value);
          }}
        />
        <Form.Control
          type="text"
          placeholder="Username..."
          onChange={(e) => {
            setUsername(e.target.value);
          }}
        />
        <Button onClick={createUser}> Create User </Button>
      </Form>
      <br />
      <Table className="usersDisplay" striped bordered hover>
        <thead className="thead-light">
          <tr>
            <td>Name</td>
            <td>Age</td>
            <td>Username</td>
          </tr>
        </thead>
        <tbody>
          {usersList.map((user) => {
            return (
              <tr>
                <td>{user.name}</td>
                <td>{user.age}</td>
                <td>{user.username}</td>
              </tr>
            );
          })}
        </tbody>
      </Table>
    </div>
  );
}
