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
    registrationErrors: Object;
    clearRegistrationErrors: () => void;
    showRegistrationModal: boolean;
    setShowRegistrationModal: React.Dispatch<React.SetStateAction<boolean>>;
}

export const RegistrationContext = createContext<RegistrationContextType>({} as RegistrationContextType);

export const RegistrationProvider = (props: PropsType) => {

    const[registrationErrors, setRegistrationErrors] = useState<Object>({});
    const[showRegistrationModal, setShowRegistrationModal] = useState<boolean>(false);

    const clearRegistrationErrors = () => {
      setRegistrationErrors({});
    }

    const tryRegistration = (inputs: RegistrationDataType) => {
      clearRegistrationErrors();
      registration(inputs);
    }

    const registration = (inputs: RegistrationDataType) => {
        Axios.post(process.env.REACT_APP_API_BACKEND_URL + "/api/users", inputs, {
          headers: {
            "Content-Type": "application/json",
          },
        }).then(() => {
          setShowRegistrationModal(false);
        }).catch((error) => {
            setRegistrationErrors(error.response.data.errors);
            setShowRegistrationModal(true);
          });
      };

    return (
        <RegistrationContext.Provider
          value={{
          tryRegistration,
          registrationErrors,
          clearRegistrationErrors,
          showRegistrationModal,
          setShowRegistrationModal}}>
          {props.children}
        </RegistrationContext.Provider>
      );
}
