import React from 'react';
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import { Login, Survey, SurveyList, UsersManager, PersonalAccount} from '../'
import { AuthProvider } from '../../context/AuthContext';
import Layout from '../../components/Layout/LayOut';

function App () {

        return (
                <Router>
                            <AuthProvider>
                    <Layout>
                        <Routes>

                            <Route path="/login" element={<Login/>}/>

                            <Route path="/home" element={<SurveyList/>}/>

                            <Route path="/user/:userId" element={<PersonalAccount/>}/>

                            <Route path="/survey/:surveyId" element={<Survey/>}/>

                            <Route path="/users" element={<UsersManager/>}/>

                            <Route path="/survey" element={<SurveyList/>}/>

                        </Routes>
                    </Layout>
                    </AuthProvider>
                </Router>
           
    )
}

export { App }