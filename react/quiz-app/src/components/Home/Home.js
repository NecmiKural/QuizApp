import React, { useState, useEffect } from 'react';
import Post from "../Post/Post";
import './Home.scss';
import Box from '@mui/material/Box';
import Container from '@mui/material/Container';

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
            <Container width="sm">
                <Box sx={{
                    display: "flex",
                    flexWrap: "wrap",
                    justifyContent: "center",
                    alignItems: "center",
                    backgroundColor: "#cfe8fc",
                    height: "100vh"
                }}>
                    {
                        postList.map(post => (
                            // sending props
                            <Post userId={post.userId} userName={post.userName} title={post.title} text={post.text} />
                        ))
                    }
                </Box>

            </Container>


        );
    }
}

export default Home;