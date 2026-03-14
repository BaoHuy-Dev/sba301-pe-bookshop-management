import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { Container, Button } from "react-bootstrap";
import ShopService from "../services/ShopService";

function ShopDetail() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [shop, setShop] = useState(null);

  useEffect(() => {
    ShopService.getById(id).then((res) => setShop(res.data));
  }, [id]);

  if (!shop) return <Container className="mt-3">Loading...</Container>;

  return (
    <Container className="mt-3">
      <h2>
        <b>VIEW DETAILS</b>
      </h2>
      <div className="mt-4 ms-4">
        <p>
          <b>Shop Name:</b> {shop.name}
        </p>
        <p>
          <b>Owner:</b> {shop.owner}
        </p>
        <p>
          <b>Type:</b> {shop.type}
        </p>
        <p>
          <b>Open time:</b> {shop.openTime}
        </p>
      </div>
      <Button variant="outline-primary" onClick={() => navigate("/")}>
        Quay lai
      </Button>
    </Container>
  );
}

export default ShopDetail;
