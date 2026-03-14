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
  if (name.strim().length > 50) {
    alert("Shop name must be at most 50 characters");
    return;
  }
  if (!openTime.trim()) {
    alert("Open time is required");
    return;
  }
};