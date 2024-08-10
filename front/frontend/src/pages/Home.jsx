import axios from 'axios';
import React, { useCallback, useEffect, useState } from 'react'
import '../style/style.css';
import Swal from 'sweetalert2';
import ModalAction from '../components/ModalAction';
import Loading from '../components/Loading';
import Post from '../components/Post';


//rafc
export const Home = () => {

  const [posts,setPosts] = useState([]);
  const [isLoading,setIsLoading]=useState(true)
  const [isEdit,setIsEdit]=useState(false);
  const [post,setPost]=useState({});

  // ** State modal 
  const [show, setShow] = useState(false);

  const handleClose = () =>{
     setShow(false);
     setIsEdit(false);
    }
  const handleShow = () => setShow(true);

  const getPosts= useCallback(async()=>{
    try {
      setIsLoading(true);
      const {data }= await axios.get("/posts");
      //console.log(data.data);
      setPosts(data.data);
      setIsLoading(false);
    } catch (error) {
      setIsLoading(false);
      console.log(error.message)  
    }
  },[])
  
  useEffect(()=>{
    getPosts()
  },[getPosts]);

  const deletePost = async(id)=>{
    try {
      const {data}=await axios.delete(`/posts/${id}`);
      await getPosts()
      Swal.fire({
        position: 'center',
        icon: 'success',
        title: data.message,
        showConfirmButton: false,
        timer: 1500
      });
      //console.log(data);
    } catch (error) {
      console.log("Erro em deletar post",error.message);
    }
  };

  const selectPost =(post)=>{
    setIsEdit(true);
    setPost(post);
    handleShow();
  }
  return (
    <div className="container mt-5">

<div className='d-flex justify-content-center mb-5'>
<i className="btn btn-info fas  fa-solid fa-plus fa-3x" onClick={handleShow}></i>
      </div>
      {
        isLoading? (
     <Loading/>
        ):(
        <div className="row row-cols-1 row-cols-md-3 g-4">
        {posts.map((post) => (
       <Post 
        post={post} 
        selectPost={selectPost} 
        deletePost={deletePost}
        key={post.id}
         />
        ))}
      </div>
      )}
 <ModalAction
  show={show} 
  handleClose={handleClose}
 getPosts={getPosts}
 isEdit={isEdit}
 post={post}
 />
    </div>
  );
};