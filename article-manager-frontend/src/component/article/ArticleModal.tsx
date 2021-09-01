import {useState, useContext} from "react";
import { Form, Input } from "antd";
import { Modal, Button } from "react-bootstrap";
import { ArticleContext, ArticleDataType } from "../../context/article/ArticleContext";
import ArticleTag from "./ArticleTag";

type PropsType = {
    article: ArticleDataType;
}

export default function AticleModal(props: PropsType) {
    const [showModal, setShowModal] = useState(false);
    const {deleteArticle, updateArticle, newArticle, setNewArticle} = useContext(ArticleContext);

    const handleShow = () => {
        setNewArticle(props.article);
        setShowModal(true)};

    const handleClose = () => {
        setNewArticle({} as ArticleDataType);
        setShowModal(false)};

    const handleArticleDelete = () => {
        deleteArticle(props.article);
        handleClose();
    }

    const handleArticleUpdate = (updatedArticle: ArticleDataType) => {
        updatedArticle.id = "" + props.article.id;
        updatedArticle.tagList = newArticle.tagList;
        updateArticle(updatedArticle);
        handleClose();
    }

    const onFinishFailed = (errorInfo: any) => {
        console.log('Failed:', errorInfo);
    }

    return(
        <div className="article-container">
            <p className="article-modal-trigger" onClick={handleShow}>
                {props.article.title}
            </p>
            <div className="article-modal">
                <Modal
                show={showModal}
                onHide={handleClose}
                size="xl">
                    <Form
                        name="basic"
                        labelCol={{ span: 8 }}
                        wrapperCol={{ span: 16 }}
                        initialValues={{ remember: true }}
                        onFinish={handleArticleUpdate}
                        onFinishFailed={onFinishFailed}
                    >
                        <Modal.Body>
                            <div className="article">
                                <div className="article-title">
                                    <p>Title: </p>
                                    <Form.Item
                                    name="title"
                                    initialValue={props.article.title}
                                    rules={[{ required: true, message: 'Please input your title!' }]}>
                                        <Input/>
                                    </Form.Item>
                                </div>
                                <div className="article-description">
                                    <p>Description: </p>
                                    <Form.Item
                                    name="description"
                                    initialValue={props.article.description}
                                    rules={[{ required: true, message: 'Please input your description!' }]}>
                                        <Input.TextArea rows={5} />
                                    </Form.Item>
                                </div>
                                <div className="article-body">
                                    <p>Body: </p>
                                    <Form.Item
                                    name="body"
                                    initialValue={props.article.body}
                                    rules={[{ required: true, message: 'Please input your body!' }]}>
                                        <Input.TextArea rows={10} />
                                    </Form.Item>
                                </div>
                            </div>
                            <div className="article-tags">
                                <ArticleTag />
                            </div>
                        </Modal.Body>
                        <Modal.Footer>
                            <Button variant="warning" type="submit">Update Article</Button>
                            <Button variant="primary" onClick={handleArticleDelete}>Delete article</Button>
                            <Button variant="secondary" onClick={handleClose}>Close</Button>
                        </Modal.Footer>
                    </Form>
                </Modal>
            </div>
        </div>
    );
}
