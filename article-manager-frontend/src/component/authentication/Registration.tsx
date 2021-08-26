import {useContext} from "react";
import { Form, Input} from 'antd';
import { Modal, Button } from "react-bootstrap";
import { RegistrationContext, RegistrationDataType } from "../../context/authentication/RegistrationContext";

const formItemLayout = {
    labelCol: {
      xs: { span: 24 },
      sm: { span: 8 },
    },
    wrapperCol: {
      xs: { span: 24 },
      sm: { span: 16 },
    },
  };
  const tailFormItemLayout = {
    wrapperCol: {
      xs: {
        span: 24,
        offset: 0,
      },
      sm: {
        span: 16,
        offset: 8,
      },
    },
  };


export default function Registration() {
    const [form] = Form.useForm();
    const {tryRegistration,
        registrationError,clearRegistrationError,
        showRegistrationModal, setShowRegistrationModal} = useContext(RegistrationContext);

    const handleClose = () => {
        clearRegistrationError();
        setShowRegistrationModal(false)};

    const handleRegistration = (event: RegistrationDataType) => {
        tryRegistration(event);
        if(registrationError === "") {
            handleClose();
        }
    }

    const handleShow = () => setShowRegistrationModal(true);

     const showRegistrationErrors = () => {
        return(
        registrationError &&
            <div className="registration error-container">
                {registrationError}
            </div>);
    }

    return(
            <div className="registration authentication-menu-element">
                <Button onClick={handleShow}>
                    Registration
                </Button>
                <div className="registration-form-container">
                    <Modal
                    show={showRegistrationModal}
                    onHide={handleClose}>
                        <Modal.Header>
                            <Modal.Title>Registration</Modal.Title>
                        </Modal.Header>
                        <Modal.Body>
                            <Form
                            {...formItemLayout}
                            form={form}
                            name="register"
                            onFinish={handleRegistration}
                            scrollToFirstError
                            >
                            <Form.Item
                                name="email"
                                label="E-mail"
                                rules={[
                                {
                                    type: 'email',
                                    message: 'The input is not valid E-mail!',
                                },
                                {
                                    required: true,
                                    message: 'Please input your E-mail!',
                                },
                                ]}
                            >
                                <Input />
                            </Form.Item>

                            <Form.Item
                                name="password"
                                label="Password"
                                rules={[
                                {
                                    required: true,
                                    message: 'Please input your password!',
                                },
                                ]}
                                hasFeedback
                            >
                                <Input.Password />
                            </Form.Item>

                            <Form.Item
                                name="confirm"
                                label="Confirm Password"
                                dependencies={['password']}
                                hasFeedback
                                rules={[
                                {
                                    required: true,
                                    message: 'Please confirm your password!',
                                },
                                ({ getFieldValue }) => ({
                                    validator(_, value) {
                                    if (!value || getFieldValue('password') === value) {
                                        return Promise.resolve();
                                    }
                                    return Promise.reject(new Error('The two passwords that you entered do not match!'));
                                    },
                                }),
                                ]}
                            >
                                <Input.Password />
                            </Form.Item>

                            <Form.Item
                                name="username"
                                label="Username"
                                tooltip="What do you want others to call you?"
                                rules={[{ required: true, message: 'Please input your username!', whitespace: true }]}
                            >
                                <Input />
                            </Form.Item>
                            <Form.Item {...tailFormItemLayout}>
                                <Button type="submit">
                                    Register
                                </Button>
                            </Form.Item>
                            {showRegistrationErrors()}
                            </Form>
                        </Modal.Body>
                    </Modal>
                </div>
            </div>
    );
};
