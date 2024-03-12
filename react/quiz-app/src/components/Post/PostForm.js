import * as React from 'react';
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import CardContent from '@mui/material/CardContent';
import Avatar from '@mui/material/Avatar';
import Typography from '@mui/material/Typography';
import { Link } from "react-router-dom";
import { Button, InputAdornment, OutlinedInput } from '@mui/material';


export default function PostForm(props) {
    const { userName, userId } = props;

    const [text, setText] = React.useState('');
    const { title, setTitle } = React.useState('');

    const savePost = () => {
        // axios could be used
        // sending post to backend to save database
        fetch("/posts", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                title: title,
                userId: userId,
                text: text
            }),
        })
            .then((res) => res.json())
            .catch((err) => console.log("error", err))
    }

    const handleSubmit = () => {
        savePost();
    }

    const handleTitle = (title) => {
        setTitle(title);
    }

    const handleText = (text) => {
        setText(text);
    }

    return (
        <div>
            <Card sx={{ width: 800, margin: 5 }}>
                <CardHeader
                    sx={{ textAlign: "left" }}
                    avatar={
                        <Link className="link" to={{ pathname: '/users/' + userId }}>
                            <Avatar sx={{ background: "linear-gradient(45deg, #2196F3, 30%, #21CBF3 90%)" }} aria-label="recipe">
                                {userName.charAt(0).toUpperCase()}
                            </Avatar>
                        </Link>
                    }
                    title={<OutlinedInput
                        id="outlined-adornment-amount"
                        multiline
                        placeholder='title'
                        inputProps={{ maxLength: 25 }}
                        fullWidth
                        onChange={(input) => handleTitle(input.target.value)}
                    >
                    </OutlinedInput>}
                />
                <CardContent>
                    <Typography variant="body2" color="text.secondary">
                        <OutlinedInput
                            id="outlined-adornment-amount"
                            multiline
                            placeholder='text'
                            inputProps={{ maxLength: 250 }}
                            fullWidth
                            onChange={(input) => handleText(input.target.value)}
                            endAdornment={
                                <InputAdornment position='end'>
                                    <Button
                                        variant='contained'
                                        style={{
                                            background: "linear-gradient(45deg, #2196F3, 30%, #21CBF3 90%)",
                                            color: 'white'
                                        }}
                                        onClick={handleSubmit}>
                                        Post
                                    </Button>
                                </InputAdornment>
                            }>
                        </OutlinedInput>
                    </Typography>
                </CardContent>
            </Card>
        </div>
    );
}

