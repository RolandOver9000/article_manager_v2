import { useContext } from "react";
import { Button } from "react-bootstrap";
import { useHistory } from "react-router-dom";
import { LogoutContext } from "../../context/authentication/LogoutContext";

export default function Header () {
    const {logout} = useContext(LogoutContext);

    const history = useHistory();

    const routeToHome = () =>{
        let path = "/";
        history.push(path);
    }

    const routeToArticles = () =>{
        let path = "/articles";
        history.push(path);
    }

    const routeToUserProfile = () => {
        let path = "/profile"
        history.push(path);
    }

    const routeToUserManager = () => {
        let path = "/user-manager"
        history.push(path);
    }

    return(
        <div className="navigation-bar">
            <div className="navigation-buttons">
                <Button onClick={routeToHome}>Home</Button>
                <Button onClick={routeToArticles}>Articles</Button>
                <Button onClick={routeToUserProfile}>Profile</Button>
                <Button onClick={routeToUserManager}>User manager</Button>
                <Button onClick={logout}>Logout</Button>
            </div>
        </div>
    );
}
