import './App.css';
// import {Route, BrowserRouter} from 'react-router-dom';
import Navbar from "./components/Navbar/Navbar";
import Home from "./components/Home/Home";
import User from "./components/User/User";
import {BrowserRouter, Route, Routes} from "react-router-dom";

function App() {
    return (
        <div className="App">
            {/*single page sağlıyor*/}
            <BrowserRouter>
                <Navbar></Navbar>
                <Routes>
                    <Route exact path="/" component={Home}></Route>
                    <Route exact path="/users/:userId" component={User}></Route>
                    {/*<Route path="/" exact component={Home}/>*/}
                    {/*<Route path="/about" component={About}/>*/}
                </Routes>
            </BrowserRouter>
        </div>
    );
}

export default App;
