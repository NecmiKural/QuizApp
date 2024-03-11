import React from 'react';
import {Link} from "react-router-dom";

function Navbar() {
    // şimdilik statik kalsın
    let userId = 8;
    return (
        <div>
            {/*<nav>*/}
            <ul>
                <li>
                    <Link to="/">Home</Link>
                </li>
                <li>
                    <Link to={{pathname: '/users/' + userId}}>User</Link>
                </li>
            </ul>
            {/*</nav>*/}
        </div>
    )
}

export default Navbar;