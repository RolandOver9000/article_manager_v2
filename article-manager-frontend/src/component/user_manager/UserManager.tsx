import { useContext, useEffect } from "react";
import { UserContext } from "../../context/user/UserContext";
import UserDataCard from "../user_profile/UserDataCard";
import Header from "../util/Header"

export default function UserManager() {
    const { getAllUsersData, allUsersData} = useContext(UserContext);

    useEffect(() => {
        getAllUsersData();
    }, [])

    return(
        <div className="user-manager-container">
            <Header />
            <div className="all-users-data">
                {allUsersData.map((userData, index) => (
                    <UserDataCard
                    key={index}
                    userProfileData={userData}/>
            ))}
            </div>
        </div>
    );
}
