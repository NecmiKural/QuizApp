import React, {useState, useEffect} from 'react';
import Post from "../Post/Post";
import './Home.scss';

function Home() {

    // the states we need. we will initialize here and then use in forward
    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [postList, setPostList] = useState([]);

    useEffect(() => {
        fetch("/posts")
            .then(r => r.json())
            .then((result) => {
                setIsLoaded(true);
                setPostList(result);
            }, (error) => {
                setIsLoaded(true);
                setError(error);
            })
    }, [])

    if (error) {
        return <div>Error: {error.message}</div>;
    } else if (!isLoaded) {
        return <div>Loading...</div>;
    } else {
        return (
            <div className='container'>
                Home
                {
                    postList.map(post => (
                        <Post title={post.title} text={post.text}/>

                    ))
                }
            </div>
        );
    }
}

export default Home;