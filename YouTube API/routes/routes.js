import express from "express";
import { findLinkYoutube } from "../controller/youtube.js";


const router = express.Router();
router.get("/youtube", findLinkYoutube);

export default router;