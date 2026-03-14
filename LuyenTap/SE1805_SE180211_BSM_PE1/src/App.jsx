import { Route, Route, Routes } from "react-router-dom";
import ShopList from "./components/ShopList";
import ShopDetail from "./components/ShopDetail";

function App() {
  return (
    <Routes>
      <Route path="/" element={<ShopList />} />
      <Route path="/shops/:id" element={<ShopDetail />} />
    </Routes>
  );
}

export default App;
