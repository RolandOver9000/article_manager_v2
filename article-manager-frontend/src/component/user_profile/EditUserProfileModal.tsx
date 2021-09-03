import { useState } from 'react';
import { Form, Input } from 'antd';
import { Modal, Button } from "react-bootstrap";
import { useContext } from 'react';
import { UserContext, UserProfileDataType } from '../../context/user/UserContext';

export default function EditUserProfileModal() {
    const [show, setShow] = useState(false);
    const {userData, updateUserProfile} = useContext(UserContext);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const onFinishFailed = (errorInfo: any) => {
        console.log('Failed:', errorInfo);
    }

    const tryUpdateUserProfile = (updatedUserProfileData: UserProfileDataType) => {
        updatedUserProfileData.id = userData.id;
        updateUserProfile(updatedUserProfileData);
        handleClose();
    }

    return(
        <div className="edit-user-profile-modal-container">
            <Button onClick={handleShow}>
                    Edit user profile
            </Button>
            <Modal
            show={show}
            onHide={handleClose}>
                <Modal.Body>
                    <Form
                        name="basic"
                        labelCol={{ span: 8 }}
                        wrapperCol={{ span: 16 }}
                        initialValues={{ remember: true }}
                        onFinish={tryUpdateUserProfile}
                        onFinishFailed={onFinishFailed}
                    >
                        <Form.Item
                            label="Bio"
                            name="bio"
                            initialValue={userData.bio}
                            rules={[{ required: false }]}
                        >
                            <Input />
                        </Form.Item>

                        <Form.Item
                            label="Email"
                            name="email"
                            initialValue={userData.email}
                            rules={[{ required: true, message: 'Please input your email!' }]}
                        >
                            <Input />
                        </Form.Item>

                        <Form.Item
                            label="Profile image link"
                            name="image"
                            initialValue={userData.image}
                            rules={[{ required: false }]}
                        >
                            <Input />
                        </Form.Item>

                        <Form.Item
                            label="Username"
                            name="username"
                            initialValue={userData.username}
                            rules={[{ required: true, message: 'Please input your username!' }]}
                        >
                            <Input />
                        </Form.Item>

                        <Modal.Footer style={{
                            display: "flex",
                            justifyContent: "center"
                        }}>
                            <Button type="submit">
                                Update profile
                            </Button>
                        </Modal.Footer>
                    </Form>
                </Modal.Body>
            </Modal>
        </div>
    );
}
