import './App.css';
import { useState,useEffect } from 'react';

function App() {

  const [data,setData] = useState(null);

  useEffect(() => {
    fetch('http://localhost:80/')
    .then(response => response.json())
    .then(json => setData(json));
  },[]);

  return (
    <div className="App">
      <div>
        {data ? `ID: ${data.id}` : 'Loading...'}
      </div>
    </div>
  );
}

export default App;
