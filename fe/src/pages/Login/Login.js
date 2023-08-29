import {LockOutlined, UserOutlined} from '@ant-design/icons';
import {Button, Card, Form, Input, Typography} from 'antd';
import {Trans, useTranslation} from "react-i18next";
import { AuthContext } from '../../context/AuthContext';
import { useState,useContext} from 'react';
import Layout from '../../components/Layout/LayOut';
import {useNavigate} from 'react-router-dom';
const {Title,Text} = Typography;

const Login = () => {

    const { t } = useTranslation();
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const { login } = useContext(AuthContext);
    let navigate = useNavigate();


    const handleLogin = async () => {
     const success = await login(username, password);
     console.log(success);
     if(success ){
        navigate("/home");
     }
     else {
        navigate("/login");
     }
      };

      const onFinish =()=>{
        handleLogin();
      };
  return (
  <Layout >
       <div
      style={{
      display: "flex",
      justifyContent: "center",
      alignItems: "center",
      height: "100vh",
      color: "blue"
    }}>
    <Card style = {{width: 500}}>

    <Form
      name="normal_login"
      className="login-form"
      initialValues={{
        remember: true,
      }}
      onFinish={onFinish}
    >
        <Form.Item >
        <Title level = {2}>Smart Survey</Title>
            <Text type = "secondary">
                <Trans t={t}>login.description</Trans>
            </Text>
        </Form.Item>

      <Form.Item
        name="username"
        rules={[
          {
            required: true,
            message: <Trans t={t}>login.username_message</Trans>,
          },
        ]}
      >
        <Input
        prefix={<UserOutlined className="site-form-item-icon" />}
         placeholder="Username"
         value={username}
         onChange={(e)=>setUsername(e.target.value)} />
      </Form.Item>
      <Form.Item
        name="password"
        rules={[
          {
            required: true,
            message: <Trans t={t}>login.password_message</Trans>
          },
        ]}
      >
        <Input.Password
          prefix={<LockOutlined className="site-form-item-icon" />}
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
      </Form.Item>
      <Form.Item>
        <Button
          type="primary"
          htmlType="submit"
          className="login-form-button"
          >
            <Trans t={t}>login.button</Trans>
        </Button>
      </Form.Item>
    </Form>
        </Card>
    </div>
  </Layout>
  );
};

export {Login};
