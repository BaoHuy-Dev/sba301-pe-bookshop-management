import { useState, useEffect } from "react";
import {
  Table,
  Button,
  Form,
  Modal,
  Container,
  Row,
  Col,
} from "react-bootstrap";
import { Link } from "react-router-dom";
import ShopService from "../services/ShopService";

function ShopList() {
  const [shops, setShops] = useState([]);
  const [types, setTypes] = useState([]);
  const [name, setName] = useState("");
  const [openTime, setOpenTime] = useState("");
  const [owner, setOwner] = useState("");
  const [type, setType] = useState("");

  const [showConfirm, setShowConfirm] = useState(false);
  const [deleteTarget, setDeleteTarget] = useState(null);

  useEffect(() => {
    loadShops();
    loadTypes();
  }, []);

  const loadShops = () => {
    ShopService.getAll().then((res) => setShops(res.data));
  };

  const loadTypes = () => {
    ShopService.getTypes().then((res) => setTypes(res.data));
  };

  const handleAddNew = () => {
    if (!name.trim()) {
      alert("Shop name is required");
      return;
    }
    if (name.trim().length > 50) {
      alert("Shop name must be at most 50 characters");
      return;
    }
    if (!openTime) {
      alert("Open time is required");
      return;
    }
    const openTimeNum = Number(openTime);
    if (isNaN(openTimeNum) || openTimeNum <= 0 || openTimeNum >= 12) {
      alert("Open time must be greater than 0 and less than 12");
      return;
    }
    if (!owner.trim()) {
      alert("Owner is required");
      return;
    }
    if (owner.trim().length > 100) {
      alert("Owner must be at most 100 characters");
      return;
    }
    if (!type) {
      alert("Type is required");
      return;
    }

    const shop = {
      name: name.trim(),
      openTime: openTimeNum,
      owner: owner.trim(),
      type,
    };

    ShopService.create(shop)
      .then(() => {
        alert("Created new Shop successfully");
        loadShops();
        setName("");
        setOpenTime("");
        setOwner("");
        setType("");
      })
      .catch((err) => {
        alert(err.response?.data?.message || "Error creating shop");
      });
  };

  const handleDeleteClick = (shop) => {
    setDeleteTarget(shop);
    setShowConfirm(true);
  };

  const handleDeleteConfirm = () => {
    ShopService.deleteShop(deleteTarget.id).then(() => {
      alert("Deleted successfully");
      loadShops();
      setShowConfirm(false);
      setDeleteTarget(null);
    });
  };

  const handleDeleteClose = () => {
    setShowConfirm(false);
    setDeleteTarget(null);
  };

  return (
    <Container>
      <h2 className="mt-3 mb-3">
        <b>Book Shop Management</b>
      </h2>
      <Form>
        <Form.Group as={Row} className="mb-2">
          <Form.Label column sm={2} className="text-end">
            Shop name:
          </Form.Label>
          <Col sm={10}>
            <Form.Control
              type="text"
              value={name}
              onChange={(e) => setName(e.target.value)}
              maxLength={50}
            />
          </Col>
        </Form.Group>
        <Form.Group as={Row} className="mb-2">
          <Form.Label column sm={2} className="text-end">
            Open time:
          </Form.Label>
          <Col sm={10}>
            <Form.Control
              type="text"
              value={openTime}
              onChange={(e) => setOpenTime(e.target.value)}
              maxLength={2}
            />
          </Col>
        </Form.Group>
        <Form.Group as={Row} className="mb-2">
          <Form.Label column sm={2} className="text-end">
            Owner:
          </Form.Label>
          <Col sm={10}>
            <Form.Control
              type="text"
              value={owner}
              onChange={(e) => setOwner(e.target.value)}
              maxLength={100}
            />
          </Col>
        </Form.Group>
        <Form.Group as={Row} className="mb-2">
          <Form.Label column sm={2} className="text-end">
            Type:
          </Form.Label>
          <Col sm={10}>
            <Form.Select value={type} onChange={(e) => setType(e.target.value)}>
              <option value="">-- Select --</option>
              {types.map((t) => (
                <option key={t} value={t}>
                  {t}
                </option>
              ))}
            </Form.Select>
          </Col>
        </Form.Group>
        <Row className="mb-3">
          <Col sm={{ span: 10, offset: 2 }}>
            <Button variant="primary" onClick={handleAddNew}>
              Add New
            </Button>
          </Col>
        </Row>
      </Form>

      <h4>
        <b>Shop List</b>
      </h4>
      <Table bordered hover>
        <thead>
          <tr>
            <th># No</th>
            <th>Shop Name</th>
            <th>Type</th>
            <th>Owner</th>
            <th>Open time</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {shops.map((shop, index) => (
            <tr key={shop.id}>
              <td>{String(index + 1).padStart(2, "0")}</td>
              <td>{shop.name}</td>
              <td>{shop.type}</td>
              <td>{shop.owner}</td>
              <td>{shop.openTime}</td>
              <td>
                <Button
                  variant="danger"
                  size="sm"
                  onClick={() => handleDeleteClick(shop)}
                >
                  Delete
                </Button>
                {" | "}
                <Link to={`/shop/${shop.id}`}>View</Link>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>

      <Modal show={showConfirm} onHide={handleDeleteClose} centered>
        <Modal.Header closeButton>
          <Modal.Title>Confirmation</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          Are you sure you want to delete "{deleteTarget?.name}...."?
        </Modal.Body>
        <Modal.Footer>
          <Button variant="primary" onClick={handleDeleteConfirm}>
            Yes
          </Button>
          <Button variant="secondary" onClick={handleDeleteClose}>
            Close
          </Button>
        </Modal.Footer>
      </Modal>
    </Container>
  );
}

export default ShopList;
