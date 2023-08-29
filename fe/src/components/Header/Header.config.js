import {Avatar} from 'antd';
import {UserOutlined} from '@ant-design/icons';
import {NavLink} from "react-router-dom";
import {logoStyle, space, verticalCenter} from "./Header.style.js";
import {Trans} from "react-i18next";
import {LanguageSelector} from "./LanguageSelector.js"

const items = (username, t) => [
    {

        label: (
            <NavLink to="/home" style={logoStyle}>Smart Surveys</NavLink>
        ),
        key: 'smartsurveys',
        style: verticalCenter

    },
    {
        label: (
            <NavLink to="/home" ><Trans t={t}>home</Trans></NavLink>
        ),
        key: 'home',
        style: verticalCenter
    },
    {
        label:(
            <NavLink to = "/login"><Trans t = {t}>logout</Trans></NavLink>

        ),
        key:'logout',

    },

    {
        label: (
            <span></span>
        ),
        key: 'space',
        style: space
    },
    {
        label: (
            <NavLink to="/user/:userId">
                <Avatar size="small" icon={<UserOutlined />}/>
                <span> </span>
                <span>{username}</span>
            </NavLink>
        ),
        key: 'avatar',
        style: verticalCenter
    },
    {
        label: (
            LanguageSelector()
        ),
        key: 'change_languages',
        style: verticalCenter
    }
]

export { items }