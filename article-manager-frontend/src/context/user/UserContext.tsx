import Axios from "axios";
import { createContext, PropsWithChildren, useState } from "react";

type PropsType = {
    children: PropsWithChildren<{}>
}

export type UserProfileDataType = {
    bio: string;
    email: string;
    id: number;
    image: string;
    username: string;
}

interface UserContextType {
    userData: UserProfileDataType;
    getLoggedInUserData: () => void;
    updateUserProfile: (value: UserProfileDataType) => void;
    getAllUsersData: () => void;
    allUsersData: UserProfileDataType[];
    deleteUserByEamil: (value: string) => void;
}

export const UserContext = createContext<UserContextType>({} as UserContextType);

export const UserProvider = (props: PropsType) => {
    const [userData, setUserData] = useState<UserProfileDataType>({} as UserProfileDataType);
    const [allUsersData, setAllUsersData] = useState<UserProfileDataType[]>([{}] as UserProfileDataType[]);

    const getLoggedInUserData = () => {
        Axios.get(process.env.REACT_APP_API_BACKEND_URL + "/api/user", {
        headers: {
            "Content-Type": "application/json",
        },
        withCredentials: true
    }).then((resp) => {
        setUserData(resp.data.user);
    })
    }

    const updateUserProfile = (updatedProfile: UserProfileDataType) => {
        Axios.put(process.env.REACT_APP_API_BACKEND_URL + "/api/user", updatedProfile, {
            headers: {
                "Content-Type": "application/json",
            },
            withCredentials: true
        }).then(() => {
            getLoggedInUserData();
            if(allUsersData.length !== 0) {
                getAllUsersData();
                }
        })
    }

    const getAllUsersData = () => {
        Axios.get(process.env.REACT_APP_API_BACKEND_URL + "/api/users", {
            headers: {
                "Content-Type": "application/json",
            },
            withCredentials: true
        }).then((resp) => {
            setAllUsersData(resp.data);
        })
    }

    const deleteUserByEamil = (email: string) => {
        Axios.delete(process.env.REACT_APP_API_BACKEND_URL + "/api/users/" + email, {
            headers: {
                "Content-Type": "application/json",
            },
            withCredentials: true
        }).then(() => {
            getAllUsersData();
        })
    }

    return(
        <UserContext.Provider
        value={{
            userData,
            getLoggedInUserData,
            updateUserProfile,
            getAllUsersData,
            allUsersData,
            deleteUserByEamil
        }}>
            {props.children}
        </UserContext.Provider>
    );
}
