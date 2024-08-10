import PropTypes from "prop-types";

const Post = ({post,selectPost,deletePost}) => {
  return (
    <div className="col" key={post.id}>
    <div className="card h-100">
      <img src={post.imgUrl} className="card-img-top" alt="..." />
      <div className="card-body">
        <h5 className="card-title">{post.title}</h5>
        <p className="card-text">{post.description}</p>
      </div>
      <div className="card-footer">
        <div className="btn-container">
          <i className='btn btn-info fa-solid fa-pen-to-square' onClick={()=>selectPost(post)}/>
          <i className='btn btn-danger fa-solid fa-trash' onClick={()=>deletePost(post.id)}/>
        </div>
      </div>
    </div>
  </div>
  )
}

export default Post

Post.prototypes={
  post:PropTypes.shape({
    id:PropTypes.string,
    imgUrl:PropTypes.string,
    title:PropTypes.string,
    description:PropTypes.string,

   }).isRequired,
   selectPost:PropTypes.func.isRequired,
  deletePost:PropTypes.func.isRequired,
};
