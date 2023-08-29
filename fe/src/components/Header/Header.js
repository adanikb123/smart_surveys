import {Menu} from 'antd';
import {items} from './Header.config.js'
import {useTranslation} from "react-i18next";
import { AuthContext } from "../../context/AuthContext"
import { useContext } from "react";

function Header() {
        const { t } = useTranslation();

        const  context  = useContext(AuthContext);


        return (
            <Menu selectable={false} theme = "dark" mode = "horizontal" items={items(context.currentUser.username, t)}/>
    )
}

export { Header }