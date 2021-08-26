import {useContext} from "react";
import { Form, Input } from 'antd';
import { Modal, Button } from "react-bootstrap";
import { LoginContext } from "../../context/authentication/LoginContext";

export default function Login() {
    const {tryLogin, loginErrors, clearLoginErrors, showLoginModal, setShowLoginModal} = useContext(LoginContext);

    const handleClose = () => {
        clearLoginErrors();
        setShowLoginModal(false)};

    const handleShow = () => setShowLoginModal(true);

    const onFinishFailed = (errorInfo: any) => {
        console.log('Failed:', errorInfo);
    }

    const showLoginErrors = () => {
        return(
        Object.keys(loginErrors).length !== 0 &&
            <div className="login error-container">
                {Object.entries(loginErrors).map((error, index) => (
                    <p key={index}>{error}</p>
                ))}
            </div>);
    }

    return(
        <div className="login authentication-menu-element">
            <Button onClick={handleShow}>
                Login
            </Button>
            <div className="login-form-container">
                <Modal
                show={showLoginModal}
                onHide={handleClose}>
                    <Modal.Header>
                        <Modal.Title>Login</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form
                            name="basic"
                            labelCol={{ span: 8 }}
                            wrapperCol={{ span: 16 }}
                            initialValues={{ remember: true }}
                            onFinish={tryLogin}
                            onFinishFailed={onFinishFailed}
                        >
                        <Form.Item
                            label="Email"
                            name="email"
                            rules={[{ required: true, message: 'Please input your email!' }]}
                        >
                            <Input />
                        </Form.Item>

                        <Form.Item
                            label="Password"
                            name="password"
                            rules={[{ required: true, message: 'Please input your password!' }]}
                        >
                            <Input.Password />
                        </Form.Item>

                        <Form.Item wrapperCol={{ offset: 8, span: 16 }}>
                            <Button type="submit">
                                Login
                            </Button>
                        </Form.Item>
                        {showLoginErrors()}
                        </Form>
                    </Modal.Body>
                </Modal>
            </div>
        </div>
    );
};
