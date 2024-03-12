import * as React from 'react';
import { styled } from '@mui/material/styles';
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';
import Collapse from '@mui/material/Collapse';
import Avatar from '@mui/material/Avatar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import FavoriteIcon from '@mui/icons-material/Favorite';
import CommentIcon from '@mui/icons-material/Comment';
import { Link } from "react-router-dom";
import { Container } from '@mui/material';
import Comment from '../Comment/Comment';
import CommentForm from '../Comment/CommentForm';

const ExpandMore = styled((props) => {
    const { expand, ...other } = props;
    return <IconButton {...other} />;
})(({ theme, expand }) => ({
    //lets keep xd
    transform: !expand ? 'rotate(0deg)' : 'rotate(180deg)',
    marginLeft: 'auto',
    transition: theme.transitions.create('transform', {
        duration: theme.transitions.duration.shortest,
    })
}));

export default function Post(props) {
    const { title, text, userName, userId, postId } = props;

    const [expanded, setExpanded] = React.useState(false);
    const [liked, setLiked] = React.useState(false);
    const [error, setError] = React.useState(null);
    const [isLoaded, setIsLoaded] = React.useState(false);
    const [commentList, setCommentList] = React.useState([]);
    // bu ilk kez mi reload ediliyor yoksa birisi commentleri açtı mı onu haber verecek
    const isInitialMount = React.useRef(true);

    const handleExpandClick = () => {
        setExpanded(!expanded);
        refreshComments();
        console.log(commentList);
    };
    const handleLike = () => {
        setLiked(!liked);
    };

    const refreshComments = () => {
        fetch("/comments?postId=" + postId)
            .then(res => res.json())
            .then(
                (result) => {
                    setIsLoaded(true);
                    setCommentList(result)
                },
                (error) => {
                    setIsLoaded(true);
                    setError(error);
                }
            )
    }

    React.useEffect(() => {
        if (isInitialMount.current) {
            isInitialMount.current = false;
        } else {
            refreshComments();
        }
    }, [commentList])

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
                    title={title}
                />
                <CardContent>
                    <Typography variant="body2" color="text.secondary">
                        {text}
                    </Typography>
                </CardContent>
                <CardActions disableSpacing>
                    <IconButton
                        onClick={handleLike}
                        aria-label="add to favorites">
                        <FavoriteIcon style={liked ? { color: "red" } : null} />
                    </IconButton>
                    <ExpandMore
                        expand={expanded}
                        onClick={handleExpandClick}
                        aria-expanded={expanded}
                        aria-label="show more"
                    >
                        <CommentIcon />
                    </ExpandMore>
                </CardActions>
                <Collapse in={expanded} timeout="auto" unmount OnExit>
                    <Container fixed>
                        {
                            error ? "error" :
                                isLoaded ? commentList.map(comment => (
                                    // <Comment key={comment.commentId} text={comment.text} userId={comment.userId} userName={comment.userName} />
                                    <Comment text={comment.text} userId={"1"} userName={"user"} />
                                )) : "loading"
                        }
                        <CommentForm postId={postId} userId={"1"} userName={"user"}></CommentForm>
                    </Container>
                </Collapse>
            </Card>
        </div>
    );
}

