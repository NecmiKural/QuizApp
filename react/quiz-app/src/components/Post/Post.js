import React from 'react';
import './Post.scss';

function Post(props) {
    const { title, text } = props;

    return (
        <div className='postContainer'>
            <h1>{title}</h1>
            <p>{text}</p>
        </div>
    );
}

export default Post;