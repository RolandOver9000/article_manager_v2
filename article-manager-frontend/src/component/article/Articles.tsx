import { useState, useContext, useEffect } from 'react';
import { Form, Input } from 'antd';
import { Modal, Button } from "react-bootstrap";
import ArticleTag from './ArticleTag';
import { ArticleContext, ArticleDataType } from '../../context/article/ArticleContext';
import ArticleModal from './ArticleModal';
import Header from '../util/Header';

export default function Article() {
    const [show, setShow] = useState(false);
    const {articles, newArticle, saveArticle, getArticles} = useContext(ArticleContext);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const onFinishFailed = (errorInfo: any) => {
        console.log('Failed:', errorInfo);
    }

    const handleArticleFormSubmit = (formValue: ArticleDataType) => {
        formValue.tagList = newArticle.tagList;
        saveArticle(formValue);
        handleClose();
    }

    useEffect(() => {
        getArticles();
    }, [])

    const handleArticles = () => {
        if(articles !== undefined){
            return articles.map((article) => (
                    <ArticleModal
                    article={article}
                    key={article.id}/>
                    ))
        } else {
            return <p>Still no articles.</p>
            }
    }

    return(
        <div className="articles">
            <Header />
            <div className="article-form-container">
            <Button onClick={handleShow}>
                Add article
            </Button>
                <Modal
                show={show}
                onHide={handleClose}>
                    <Modal.Header>
                        <Modal.Title>New Article Form</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form
                            name="basic"
                            labelCol={{ span: 8 }}
                            wrapperCol={{ span: 16 }}
                            initialValues={{ remember: true }}
                            onFinish={handleArticleFormSubmit}
                            onFinishFailed={onFinishFailed}
                        >
                        <Form.Item
                            label="Title"
                            name="title"
                            rules={[{ required: true, message: 'Please input your title!' }]}
                        >
                            <Input />
                        </Form.Item>

                        <Form.Item
                            label="Description"
                            name="description"
                            rules={[{ required: true, message: 'Please input your description!' }]}
                        >
                            <Input.TextArea />
                        </Form.Item>

                        <Form.Item
                            label="Body"
                            name="body"
                            rules={[{ required: true, message: 'Please input your body!' }]}
                        >
                            <Input.TextArea />
                        </Form.Item>

                        <Form.Item
                            label="Tags"
                            name="tagList">
                                <ArticleTag/>
                        </Form.Item>

                        <Form.Item wrapperCol={{ offset: 8, span: 16 }}>
                            <Button type="submit">
                                Create article
                            </Button>
                        </Form.Item>
                        </Form>
                    </Modal.Body>
                </Modal>
            </div>
            <div className="articles-container">
                {handleArticles()}
            </div>
        </div>
    );
}
