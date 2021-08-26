import { createContext, PropsWithChildren, useState } from "react";
import Axios from "axios";


type PropsType = {
    children: PropsWithChildren<{}>
}

export type RegistrationDataType = {
    username: string;
    password: string;
    email: string;
}

export interface RegistrationContextType {
    tryRegistration: (inputs: RegistrationDataType) => void;
    registrationError: String;
    clearRegistrationError: () => void;
    showRegistrationModal: boolean;
    setShowRegistrationModal: React.Dispatch<React.SetStateAction<boolean>>;
}

export const RegistrationContext = createContext<RegistrationContextType>({} as RegistrationContextType);

export const RegistrationProvider = (props: PropsType) => {

    const[registrationError, setRegistrationError] = useState<String>("");
    const[showRegistrationModal, setShowRegistrationModal] = useState<boolean>(false);

    const clearRegistrationError = () => {
      setRegistrationError("");
    }

    const tryRegistration = (inputs: RegistrationDataType) => {
      clearRegistrationError();
      registration(inputs);
    }

    const registration = (inputs: RegistrationDataType) => {
        Axios.post(process.env.REACT_APP_API_BACKEND_URL + "/auth/registration", inputs, {
          headers: {
            "Content-Type": "application/json",
          },
        }).then(() => {
          setShowRegistrationModal(false);
        }).catch((error) => {
            setRegistrationError(error.response.data.message);
            setShowRegistrationModal(true);
          });
      };

    return (
        <RegistrationContext.Provider
          value={{
          tryRegistration,
          registrationError,
          clearRegistrationError,
          showRegistrationModal,
          setShowRegistrationModal}}>
          {props.children}
        </RegistrationContext.Provider>
      );
}
