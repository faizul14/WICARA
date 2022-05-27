import Youtube from "../models/youtube.js";


export const findLinkYoutube = async(req, res)=>{
    try {
        const youtube  = await Youtube.findAll()
        res.json(youtube)
    }catch(err){
        res.json({message: err})
    };
};

export default findLinkYoutube