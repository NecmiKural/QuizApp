import React, { useState, useEffect } from 'react';
import Post from "../Post/Post";
import './Home.scss';
import Box from '@mui/material/Box';
import PostForm from '../Post/PostForm';

function Home() {

    // the states we need. we will initialize here and then use in forward
    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [postList, setPostList] = useState([]);

    const refreshPosts = () => {
        fetch("/posts")
            .then(r => r.json())
            .then((result) => {
                setIsLoaded(true);
                setPostList(result);
            }, (error) => {
                setIsLoaded(true);
                setError(error);
            })
    }

    useEffect(() => {
        refreshPosts();
    }, [postList])

    if (error) {
        return <div>Error: {error.message}</div>;
    } else if (!isLoaded) {
        return <div>Loading...</div>;
    } else {
        return (
            <Box sx={{
                display: "flex",
                flexWrap: "wrap",
                justifyContent: "center",
                alignItems: "center",
                backgroundColor: "#f0f5ff",
                height: "100%",
            }}>
                <PostForm userId={1} userName={"post.userName"} refreshPosts={refreshPosts} ></PostForm>
                {
                    postList.map(post => (
                        // sending props
                        <Post likes={post.postLikes} postId={post.id} userId={post.userId} userName={post.userName}
                            title={post.title} text={post.text} />
                    ))
                }
            </Box>
        );
    }
}

export default Home;