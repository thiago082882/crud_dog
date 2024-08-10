import {Button, Modal} from 'react-bootstrap'
import { PropTypes } from 'prop-types'
import { useEffect, useState } from 'react';
import axios from 'axios';
import Swal from 'sweetalert2';


const initialState={
    title: "",
    description:"",
    imgUrl:""
};
export const ModalAction = ({show,handleClose,getPosts,isEdit,post}) => {
   const [formulario,setFormulario]=useState(initialState);

  useEffect(()=>{
   isEdit?setFormulario(post):setFormulario(initialState);
  },[isEdit,post]);


   const handleChange=(e)=>{
    setFormulario({...formulario,[e.target.name]:e.target.value})
  };
   
  const savePost= async()=>{
try {
  const {data} = await axios.post("/posts",formulario);
  Swal.fire({
    position: 'center',
    icon: 'success',
    title: data.message,
    showConfirmButton: false,
    timer: 1500
  });
  await getPosts();
  resetFormulario();
  handleClose();
} catch (error) {
  console.log("Erro em salvar Post",error.message);
}
  };
   


  const updatePost= async()=>{
    try {
      const {data} = await axios.put(`/posts/${post.id}`,formulario);
      Swal.fire({
        position: 'center',
        icon: 'success',
        title: data.message,
        showConfirmButton: false,
        timer: 1500
      });
      await getPosts();
      resetFormulario();
      handleClose();
    } catch (error) {
      console.log("Erro em editar Post",error.message);
    }
      };
   
      
  const handleSubmit=async(e)=>{
    e.preventDefault();
try {
  console.log(formulario);
  isEdit? await updatePost() : await savePost();
} catch (error) {
  console.log("Erro em Handle Submit",error.message);
}
  };

  const resetFormulario=()=>setFormulario(initialState);

  

  return(
    <Modal show={show} onHide={handleClose}>
    <Modal.Header closeButton>
    <Modal.Title>{isEdit ? "Editar Post":"Criar Post"}</Modal.Title>
    </Modal.Header>
    <Modal.Body>
      <form onSubmit={handleSubmit} >
               {/*title*/}
      <div className='mb-3'>
          <label className='form-label'>Titulo</label>
          <input
          type="text"
          className='form-control'
          name='title'
           onChange={(e)=>handleChange(e)}
           value={formulario.title}
           autoFocus
           required
           />
        </div>
        {/*description*/}
        <div className='mb-3'>
          <label className='form-label'>Descrição</label>
          <textarea
          rows={4}
          type="text"
          className='form-control'
          name='description'
           onChange={(e)=>handleChange(e)}
           value={formulario.description}
           required
           />
        </div>
{/*imgUrl*/}
<div className='mb-3'>
          <label className='form-label'>imgUrl</label>
          <input
          type="text"
          className='form-control'
          name='imgUrl'
           onChange={(e)=>handleChange(e)}
           value={formulario.imgUrl}
           required
           />
        </div>
  <button type='submit' className='btn btn-primary'>
    {
      isEdit ? "Atualizar" : "Guardar"
    }
  </button>
      </form>
    </Modal.Body>
    </Modal>
  );
  };
export default ModalAction

ModalAction.prototype={
   show:PropTypes.bool.isRequired,
   handleClose:PropTypes.func.isRequired,
   getPosts:PropTypes.func.isRequired,
   isEdit:PropTypes.bool.isRequired,
   post:PropTypes.shape({
    id:PropTypes.string,
    imgUrl:PropTypes.string,
    title:PropTypes.string,
    description:PropTypes.string,

   }).isRequired

};
