import { useParams } from "react-router-dom"

function Survey(){
    const param = useParams()
    return (
        <div>Survey id#{param.surveyId}</div>
    )
}

export { Survey }