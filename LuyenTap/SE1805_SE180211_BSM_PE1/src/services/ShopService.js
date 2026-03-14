import axios from "axios";

const API_URL = "http://localhost:8080/myapp/shops";

const getAll = () => axios.get(API_URL);

const getById = (id) => axios.get(`${API_URL}/${id}`);

const create = (shop) => axios.post(API_URL, shop);

const deleteShop = (id) => axios.delete(`${API_URL}/${id}`);

const getTypes = () => axios.get(`${API_URL}/types`);

export default { getAll, getById, create, deleteShop, getTypes };