import { Sequelize } from "sequelize";

const db = new Sequelize('db_tes','root','',{
    host: "localhost",
    dialect: "mysql"
})

export default db;