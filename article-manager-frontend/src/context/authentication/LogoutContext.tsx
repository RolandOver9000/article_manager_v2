import { createContext, PropsWithChildren } from "react";
import Axios from "axios";

type PropsType = {
    children: PropsWithChildren<{}>
}


export interface LogoutContextType {
    logout: () => void;
}

export const LogoutContext = createContext<LogoutContextType>({} as LogoutContextType);

export const LogoutProvider = (props: PropsType) => {

    const logout = () => {
        Axios.post(
            process.env.REACT_APP_API_BACKEND_URL + "/auth/logout","",
            {
              headers: {
                "Content-Type": "application/json",
              },
              withCredentials: true
            },
          ).then(() => {
              window.location.reload();
          });
        };

    return(
        <LogoutContext.Provider
        value={{
            logout
        }}>
            {props.children}
        </LogoutContext.Provider>
    )
}
