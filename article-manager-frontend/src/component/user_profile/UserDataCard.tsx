import { useContext, useEffect } from "react";
import { Button, Card } from "react-bootstrap";
import { UserContext, UserProfileDataType } from "../../context/user/UserContext";
import EditUserProfileModal from "./EditUserProfileModal";

type PropsType = {
    userProfileData: UserProfileDataType;
}

export default function UserDataCard(props: PropsType) {
    const {userData, getLoggedInUserData, deleteUserByEamil} = useContext(UserContext);

    const getUserProfileModifierButton = () => {
        if(userData.id === props.userProfileData.id) {
            return(<EditUserProfileModal />);
        } else {
            return(
                <Button onClick={() => (
                    deleteUserByEamil(props.userProfileData.email))}>
                    Delete user profile
                </Button>
            )
        }
    }

    useEffect(() => {
        getLoggedInUserData();
    }, [])

    const checkIfPictureValid = () => {
        if(props.userProfileData.image !== "" && props.userProfileData.image !== undefined) {
            return(
            <Card.Img variant="top" src={props.userProfileData.image} />
            );
        } else {
            return(
            <Card.Img variant="top" src="holder.js/100px180" />
            );
        }
    }
    return(
        <div className="profile-data-container">
            <Card style={{ width: '18rem' }}>
                {checkIfPictureValid()}
                <Card.Body>
                    <p className="profile-data">Username: {props.userProfileData.username}</p>
                    <p className="profile-data">Id: {props.userProfileData.id}</p>
                    <p className="profile-data">Email: {props.userProfileData.email}</p>
                    <p className="profile-data">Bio: {props.userProfileData.bio}</p>
                </Card.Body>
                <Card.Footer>
                    {getUserProfileModifierButton()}
                </Card.Footer>
            </Card>
        </div>
    );
}
