import {Select} from "antd";
import {useTranslation} from "react-i18next";

function LanguageSelector() {
    const { i18n } = useTranslation();

    const changeLanguage = (value) => {
        i18n.changeLanguage(value);
    };

    return (
        <Select
            defaultValue={"en"}
            onChange={changeLanguage}
            options={[
                {value: "en", label: "EN"},
                {value: "ru", label: "RU"}
            ]}
        />
    );
}

export {LanguageSelector}