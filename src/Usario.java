public interface Usario {
    public void elegirMedioDeAutenticacion(byte metodo);
    public byte getMetodoAutenticacion();
    public String getCorreo();
    public String getTelefono();
    public String getContrasena();
    public String getNombres();
    public String getApellidos();
    public void setNombres(String nombres);
    public void setApellidos(String apellidos);
    public void setCorreo(String correo);
    public String getFechaNacimiento();
    public void setContrasena(String contrasena);
    public void setTelefono(String telefono);
    public String getPais();
    public void setFechaNacimiento(String fechaNacimiento);
    public void setPais(String pais);


}
