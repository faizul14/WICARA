import db from "../config/database.js";

const Youtube = db.define('youtube',{
    link_youtube: {
        type: String,
    },
});

export default Youtube