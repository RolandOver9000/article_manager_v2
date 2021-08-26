import { useContext, useEffect } from "react";
import { UserContext } from "../../context/user/UserContext";
import Header from "../util/Header";
import UserDataCard from "./UserDataCard";

export default function UserProfile() {
    const {getLoggedInUserData, userData} = useContext(UserContext);

    useEffect(() => {
        getLoggedInUserData();
    }, [])

    return(
        <div className="user-profile">
            <Header/>
            <UserDataCard
            userProfileData={userData} />
        </div>
    );
}
