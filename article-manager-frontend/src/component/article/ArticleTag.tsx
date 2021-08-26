
import { useContext, useState } from 'react';
import { Input, Tag } from 'antd';
import { Button } from 'react-bootstrap';
import { ArticleContext } from '../../context/article/ArticleContext';

export default function ArticleTag() {
    const {newArticle, setNewArticle} = useContext(ArticleContext);
    const [newTag, setNewTag] = useState("");

    const handleInputChange = (event: any) => {
        setNewTag(event.target.value);
    }

    const isTagExist = () => {
        const foundTag = newArticle.tagList.find((tag) => tag === newTag);
        if(foundTag !== undefined) {
            return true;
        }
        return false;
    }

    const handleTagInput = () => {
        if(newArticle.tagList === undefined) {
            setNewArticle((prevArticle) =>({
                ...prevArticle,
                tagList: [newTag]
            }))
        } else if(!isTagExist()) {
            setNewArticle(prevArticle => ({
                ...prevArticle,
                tagList: [...prevArticle.tagList, newTag]
            }))
        }
    }

    const removeTag = (removableTag: string) => {
        const filteredTags:string[] = newArticle.tagList.filter((tag) => tag !== removableTag);
        setNewArticle(prevArticle => ({
            ...prevArticle,
            tagList: filteredTags
        }))
    }

    return(
        <div className="tag-container">
            <div className="tag-input">
                <Input
                value={newTag}
                onChange={handleInputChange}
                />
                <Button id="add-tag-button" onClick={handleTagInput}>Add tag</Button>
            </div>
            <div className="tags">
                {newArticle.tagList !== undefined &&
                newArticle.tagList.map((tag, index) => (
                <Tag
                key={tag+index}
                closable
                onClose={() => {removeTag(tag)}}>
                    {tag}
                </Tag>
                ))}
            </div>
        </div>
    );

}
