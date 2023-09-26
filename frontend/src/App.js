import "./App.css";
import { Route, Routes } from "react-router-dom";
import ProductRouters from "./routers/ProductRouters";

function App() {
  return (
    <div className="">
      <Routes>
        <Route path="/*" element={<ProductRouters />}></Route>
      </Routes>
    </div>
  );
}

export default App;
