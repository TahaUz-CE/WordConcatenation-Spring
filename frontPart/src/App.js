import axios from "axios"
import { useEffect,useState } from "react"

function App() {

const [inputValues, setInputValues] = useState(['']);
const [result, setResult] = useState("");
const [time, setTime] = useState("");


  const handleChange = (index, event) => {
    const newInputValues = [...inputValues];
    newInputValues[index] = event.target.value;
    setInputValues(newInputValues);
  };

  const handleAddInput = () => {
    setInputValues([...inputValues, '']);
  };

  const handleRemoveInput = index => {
    const newInputValues = [...inputValues];
    newInputValues.splice(index, 1);
    setInputValues(newInputValues);
  };

  function handleClick() {
    const text1 = inputValues.join("-");
    axios
      .post("http://localhost:8080/DataYazlab", { text1})
      .then(response => {
        console.log(response)
        // Handle response
    })
    alert(text1);
  }

  function handleResultData() {
    axios
      .get("http://localhost:8080/DataYazlab", { result})
      .then(response => {
        setResult(response.data.result);
        setTime(response.data.time);
        console.log("tata "+response.data.result)
        // Handle response
    })
  }

  return (
    <div>
      <div className="main-Text">{"Multi Input String Process"}</div>
      <div className="side-text">{"String Input"}</div>
      {inputValues.map((inputValue, index) => (
        <div key={index}>
          {"Metin "+(index+1)+"    "}
          <input
            type="text"
            value={inputValue}
            onChange={event => handleChange(index, event)}
          />
          {inputValues.length !== 1 && (
            <button type="button" onClick={() => handleRemoveInput(index)}>
              Remove
            </button>
          )}
        </div>
      ))}
      <button type="button" onClick={handleAddInput}>
        Add New Text
      </button>
      <button onClick={handleClick}>Birleştir</button>
      <p></p>
      <div className="side-text">{"COMBINED TEXT (Text To Be Sent)"}</div>
      <div className="data-input">
      {inputValues.length > 0 && <p type= "data-input">{" "+inputValues.join("-")}</p>}
      </div>
      <div className="side-text">{"Result Text"}</div>
      <button type="button" onClick={() => handleResultData()}>
              SHOW RESULT
            </button>
      <div className="data-input">
      {<p type= "data-input">{"Result:" + result}</p>}
      {<p type= "data-input">{time}</p>}
      </div>  
    </div>
    
  );
}

export default App