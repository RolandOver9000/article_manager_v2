import React, { createContext, useState } from "react";
import { PropsWithChildren } from "react"
import Axios from "axios";
import { useCookies } from "react-cookie";


type PropsType = {
    children: PropsWithChildren<{}>
}

export type ArticleDataType = {
    title: string;
    description: string;
    body: string;
    slug: string;
    tagList: string[];
}

export interface ArticleContextType {
    articles: ArticleDataType[];
    newArticle: ArticleDataType;
    setNewArticle: React.Dispatch<React.SetStateAction<ArticleDataType>>;
    saveArticle: (value: ArticleDataType) => void;
    getArticles: () => void;
    deleteArticle: (event: ArticleDataType) => void;
    updateArticle: (event: ArticleDataType) => void;
}

export const ArticleContext = createContext<ArticleContextType>({} as ArticleContextType);

export const ArticleProvider = (props: PropsType) => {
    const [articles, setArticles] = useState<ArticleDataType[]>([{}] as ArticleDataType[]);
    const [newArticle, setNewArticle] = useState<ArticleDataType>({} as ArticleDataType);
    const [cookies, _setCookie] = useCookies([]);


    const saveArticle = (newArticle: ArticleDataType) => {
        Axios.post(process.env.REACT_APP_API_BACKEND_URL + "/api/articles", newArticle, {
            headers: {
                "Content-Type": "application/json",
                "Authorization" : `Bearer ${cookies.token}`
            },
        }).then((resp) => {
            setArticles((prevArticles) => [resp.data, ...prevArticles]);
            setNewArticle({} as ArticleDataType);
        })
    }

    const updateArticle = (updatedArticle: ArticleDataType) => {
        Axios.put(process.env.REACT_APP_API_BACKEND_URL + "/api/articles/" + updatedArticle.slug,
        updatedArticle, {
            headers: {
                "Content-Type": "application/json",
                "Authorization" : `Bearer ${cookies.token}`
            },
        }).then(() => {
            setNewArticle({} as ArticleDataType);
            getArticles();
        })
    }

    const deleteArticle = (removableArticle: ArticleDataType) => {
        Axios.delete(process.env.REACT_APP_API_BACKEND_URL + "/api/articles/" + removableArticle.slug, {
            headers: {
                "Content-Type": "application/json",
                "Authorization" : `Bearer ${cookies.token}`
            },
        }).then(() => {
            setArticles(articles.filter((article) => article !== removableArticle))
        })
    }

    const getArticles = () => {
        Axios.get(process.env.REACT_APP_API_BACKEND_URL + "/api/articles", {
            headers: {
                "Content-Type": "application/json",
                "Authorization" : `Bearer ${cookies.token}`
            },
        }).then((resp) => {
            setArticles(resp.data.articles);
        })
    }

    return(
        <ArticleContext.Provider
        value={{
            articles,
            saveArticle,
            getArticles,
            deleteArticle,
            updateArticle,
            newArticle,
            setNewArticle
        }}>
            {props.children}
        </ArticleContext.Provider>
    )
}
