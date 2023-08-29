import React from 'react';

import { useLocation } from 'react-router-dom';
import { Header } from '../Header';
import noHeader from '../../constants/noHeader';

const Layout = ({ children }) => {
  const router = useLocation();

  const { pathname } = router;
  

  return (
    <div>
      <div className="flex flex-col h-screen">
        {noHeader.includes(pathname) ? null : <Header />}
        <main className="mb-auto">{children}</main>

      </div>
    </div>
  );
};

export default Layout;