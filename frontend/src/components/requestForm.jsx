import React, { Component } from 'react';
import requestService from '../services/requestService';

class requestForm extends Component {
  

    constructor(props) {
        super(props);
        this.state = {
          questionsandanswers: [],
          question: '',
        };
        this.handleInputChange = this.handleInputChange.bind(this);
        this.handlePostRequest = this.handlePostRequest.bind(this);
    }
    handlePostRequest = async (e) => {
        e.preventDefault();
        const {question} = this.state;
        const newAnswer = await requestService.postRequest(question);
        this.setState({ questionsandanswers: newAnswer  });
    };

      handleInputChange = (event) => {
        this.setState({ question: event.target.value });
    };
    render() {
        const { question, questionsandanswers } = this.state;

        return (
        <div>
            <form onSubmit = {this.handlePostRequest}>
              <div class="form-group">
              <label for="theRequestText">What is your question ? </label>
              <input type="text" value={question} onChange={this.handleInputChange} class="form-control" id="theRequestText" placeholder="Write your question here"></input>
              </div>
              <button type="submit" class="btn btn-primary">Submit</button>
            </form>

            <table class="table table-striped table-dark">
              <thead></thead>
               <tbody>
                {questionsandanswers.map((object,index) => (
                <React.Fragment key={index}>
                <tr>
                  <td>{object.question}</td>
                </tr>
                <tr>
                  <td>{object.answer}</td>
                </tr>
              </React.Fragment>
          ))}
        </tbody>
      </table>
            
            
        </div>
        );
    };


    }
    export default requestForm;




    