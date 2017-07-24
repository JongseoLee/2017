#
#
from py_mentat import *
from py_post import *
from math import *

def param_define() :
# Dummy Routine for describing the parameters
#
#   ndiv : no. of divisions through spring center line
#   s_model : Spring Model Name  --> coil_name
#   s_d1 : Spring diameter --> wire_dia
#   c_d  : Coil Center diameter --> coil_dia
#   c_id : Coil Internal diameter --> coil_int_dia
#   c_ed : Coil External diameter --> coil_ext_dia
#   c_ud : Coil Upper diameter --> coil_up_dia
#   c_ld : Coil Lower diameter --> coil_low_dia
#   c_turn : Coil Total turns  --> coil_turn_no
#   h_stroke : Hot Setting Stroke --> hset_stroke
#   cnt_beam_start : Starting Node No. of Center Beam --> beam_st_no
#
#  coil_h : Spring Height
#  U_seat_Rad : Upper Seat Radius
#  L_seat_Rad : Lower Seat Radius
  return

def get_fname():  # get file name
   fname = py_get_string("model_name()")
   iname = fname+"_"+py_get_string("job_name()")+".dat"
   #   print "File names ==",fname,iname
   return fname,iname

def generate_spring(s_data,h_stroke,cnt_beam_start,i_no,lf) :
   print "Start Generate Spring : generate_spring "
   s_x,s_y,s_z,s_r,s_t,coil_param=s_data[0],s_data[1],s_data[2],s_data[3],s_data[4],s_data[5] 
   sl = "\n  *** Start Generating Spring Model with the below Data \n\n" 
   lf.write(sl)
   for si in range(0,len(s_x)) :
     sl=str(s_x[si])+","
     sl=sl+str(s_y[si])+","
     sl=sl+str(s_z[si])+","
     sl=sl+str(s_r[si])+","
     sl=sl+str(s_t[si]) + "\n"
     lf.write(sl)
#
#   ndiv : no. of divisions through spring center line
#   s_model : Coil Model Name : coil_name
#   s_d1 : Wire diameter  :  
#   c_d  : Coil Center diameter  :
#   c_id : Coil Internal diameter 
#   c_ed : Coil External diameter 
#   c_ud : Coil Upper diameter 
#   c_ld : Coil Lower diameter 
#   c_turn : Coil Total turns 
#   h_stroke : Hot Setting Stroke
#   cnt_beam_start : Starting Node No. of Center Beam

   s_model=coil_param[0]
   s_d1=coil_param[1] 
   c_d=coil_param[2]
   c_id=coil_param[3]
   c_ed=coil_param[4]
   c_ud=coil_param[5]
   c_ld=coil_param[6]
   c_turn=coil_param[7]
   create_spring_r2(s_x,s_y,s_z,s_r,s_t,s_d1,lf)  # coiled spring
   create_seat(s_x,s_y,s_z,s_d1)
   print "Beam Start :",cnt_beam_start
   create_center_line_elem_r2(s_d1,cnt_beam_start)
   add_material(s_model,i_no)
   # py_send("*save_as_model tmp_spring.mud yes ")
   add_align_center_elem(cnt_beam_start)   
   create_contact(h_stroke)
   add_conditions(cnt_beam_start)
   create_lcase_job()
   return

def add_align_center_elem(cnt_beam_start) : 
   print "Start Adding Align Center Element : add_align_center_elem "
#
# Should be redefine the element & Node No. later, with the count of node & element
#
   t_div=py_get_int("tdiv")
   n_div=py_get_int("ndiv")
   ht_div = int(t_div/2)
   qt_div = int(t_div/4)
   tqt_div = py_get_int("tqt_div")
   bqt_div = py_get_int("bqt_div")
   t_cnt_elem=py_get_int("t_cnt_elem")
# Define Align Node No.   

   h_node_t=cnt_beam_start+tqt_div # Half Top Turn Node No. 1000025
   print "h_node_t=",h_node_t
   cnt_elem_t=cnt_beam_start+t_cnt_elem # Top(Low locate Seat Center) Center elem No. 1000241
   print "cnt_elem_t= ",cnt_elem_t
   qt_node_t = cnt_beam_start+tqt_div/2 # quarter Top Turn Node No. 1000013
   print "qt_node_t = ",qt_node_t
   cnt_node_t = cnt_elem_t+2 # Center Top Turn Node No. 1000243
   print " cnt_node_t = ",cnt_node_t
   cnt_node_l = cnt_node_t+2 # Center Top Turn Node No. 1000245
   print "cnt_node_l =",cnt_node_l
   qt_node_l = cnt_beam_start+t_cnt_elem - bqt_div/2  # quarter Top Turn Node No. 1000230
   print " qt_node_l =",qt_node_l
   h_node_l = cnt_beam_start+t_cnt_elem - bqt_div # half Top Turn Node No. 1000217
   print " h_node_l = ",h_node_l
   # Create Top Align Element 
   py_send("*set_element_class line2 ")
   py_send("*add_elements  %d %d " %(cnt_beam_start,h_node_t)) #1000001 1000025
   py_send("*sub_divisions 2 1 1 ")
   py_send("*subdivide_elements  %d # " %(cnt_elem_t)) #1000241
   py_send("*add_elements  %d  %d " %(cnt_node_t,qt_node_t)) #1000243  1000013 
   x0,y0,z0=py_get_float("node_x(%d)"%cnt_node_t),py_get_float("node_y(%d)"%cnt_node_t),py_get_float("node_z(%d)"%cnt_node_t) #1000243
   x1,y1,z1=py_get_float("node_x(%d)"%qt_node_l),py_get_float("node_y(%d)"%qt_node_l),py_get_float("node_z(%d)"%qt_node_l) #1000229
   print " Get the corrdinate of To Center ",x0,y0,z0
   print " Get the corrdinate of Lower Center ",x1,y1,z1
   py_send("*move_reset *set_move_scale_factors 0.0 0.0 1.0 ")
   py_send("*move_nodes %d # "%cnt_node_t)  #1000243
   print cnt_node_t
   dx1,dy1,dz1=(x1-x0),(y1-y0),(z1-z0)
   py_send("*duplicate_reset ")
   py_send("*set_duplicate_translation z %f " %dz1)
   py_send("*duplicate_nodes %d # " %cnt_node_t)  #1000243
   py_send("*add_elements %d %d "%(cnt_node_l,qt_node_l)) #1000245  1000229
   py_send("*add_elements %d  %d "%(cnt_node_l,cnt_elem_t))  #1000245  1000241
   py_send("*add_elements %d  %d "%(cnt_node_l,h_node_l)) #1000245  1000217
   py_send("*edit_geometry spring_center_dummy_beam *copy_geometry ")
   py_send("*geometry_name cntr_dummy ")
   py_send("*geometry_param diameter 1 ")
   py_send("*add_geometry_elements %d to %d "%(cnt_elem_t+1,cnt_elem_t+6)) #1000242 to 1000247
   py_send("*edit_mater Center_dyummy_mat ")
   py_send("*add_mater_elements %d to %d "%(cnt_elem_t+1,cnt_elem_t+6)) #1000242 to 1000247
   py_send("*sweep_nodes all_existing ")
   # Tempoerarily save a file
   # py_send("*save_as_model tmp2.mud yes ")
   return
 
def read_coil_data(iname,lf) :
   print "Start Reading Coil Data from : read_coil_data %s " %iname  
   ds_x,ds_y,ds_z,ds_r,ds_t=[],[],[],[],[] # designed spring
   coil_param=[] 
   rf=open(iname,"r")
   s = rf.read()
   no_lines=s.count('\n')
   print (" number of lines : Coil Data ",no_lines)
   rf.seek(0) # move to first line
   sl="\n *** Start Read Coil Data *** \n\n"
   lf.write(sl)
   for i in range(1,11) :
      s1= rf.readline().strip('\n')
      s2=s1.split(',')
      if (i == 1) :
        s3=s2[1].strip()   # remove left & right blank in the string
        s4=s3.replace(" ","")  # remove blank in the string 
        s_model = s4
        coil_param.append(s_model)
        py_send("*define coil_name %s " %s_model)
        print " Spring Model Name ",s_model
      if (i == 2) :
        s_d1 = float(s2[1])
        coil_param.append(s_d1)
        py_send("*eval wire_dia %f " %s_d1)
        print " Spring diameter ",s_d1
      if (i == 3) :
        c_d = float(s2[1])
        coil_param.append(c_d)
        py_send("*eval coil_dia %f " %c_d)        
        print " Coil Central diameter ",c_d
      if (i == 4) :
        c_id = float(s2[1])
        coil_param.append(c_id)
        py_send("*eval coil_int_dia %f " %c_id)                
        print " Coil Internal diameter ",c_id
      if (i == 5) :
        c_ed = float(s2[1])
        coil_param.append(c_ed)
        py_send("*eval coil_ext_dia %f " %c_ed)                        
        print " Coil External diameter ",c_ed
      if (i == 6) :
        c_ud = float(s2[1])
        coil_param.append(c_ud)
        py_send("*eval coil_up_dia %f " %c_ud)                        
        print " Coil Upper diameter ",c_ud
      if (i == 7) :
        c_ld = float(s2[1])
        coil_param.append(c_ld)
        py_send("*eval coil_low_dia %f " %c_ld)                        
        print " Coil Lower diameter ",c_ld        
      if (i == 8) :
        c_turn = float(s2[1])
        coil_param.append(c_turn)
        py_send("*eval coil_turn_no %f " %c_turn)                        
        print " Coil Total turns NO. ",c_turn 
        py_send("*define c_turn %f " %c_turn)
   #print "Coil Parameter ",coil_param    
   k=11
   sl=" *** Coil Geometry Data(Input) *** \n"
   lf.write(sl)
   while k<= no_lines : 
     s1= rf.readline().strip('\n')
     s2=s1.split(',')     
     ns = len(s2)
     if (s2[0] != 'end') and (ns == 5)  : 
       print "Line:",k,"Spring Data :",s2
       ds_x.append(float(s2[0]))
       ds_y.append(float(s2[1]))
       ds_z.append(float(s2[2]))
       ds_r.append(float(s2[3]))
       ds_t.append(float(s2[4]))
       sl = ""
       for si in range(0,len(s2)) :
         sl = sl + str(s2[si]) + ","
       sl = sl + "\n"  
       lf.write(sl)
     k=k+1   
   # print ds_t
   rf.close()
   check_coil_data(ds_t) 
   print " Angular division no. for a turn - read_coil_data", py_get_float("a_div") 
   return  ds_x,ds_y,ds_z,ds_r,ds_t,coil_param

def check_coil_data(t_no) :
 # print t_no   
 a_div = t_no[1]-t_no[0]
 py_send("*define adiv %f" %a_div) 
 #print "Length of t_no",len(t_no)
 #print "Turn No. check : No. , Turn No Currnet, Turn No. Next " 
 for i in range(0,len(t_no)-1) :
   tn_inc = t_no[i+1] - t_no[i]
   #print "Turn No. check : ",i,t_no[i],t_no[i+1],tn_inc 
 py_send("*define c_dat_no %d " %(len(t_no)))   
 return    


def read_ic_data(icname,lf) :
   print "Start Reading Initial Conditioner Data from : read_ic_data %s " %icname  
   sl = "\n *** Start Reading Initial Conditioner *** \n\n"
   lf.write(sl) 
   ic_t,ic_r,ic_z=[],[],[] # initial condition data 
   rf=open(icname,"r")   
   s = rf.read()
   no_lines=s.count('\n')
   print (" number of lines : initial Conditioner data file ",no_lines)
   rf.seek(0) # move to first line
   s1= rf.readline().strip('\n')    
   # print "Line - Init. Con. = 1",s1     
   s1= rf.readline().strip('\n')
   # print "Line - Init. Con. = 2",s1  
   k=3
   while k<= no_lines : 
     s1= rf.readline().strip('\n')
     s2=s1.split(',')
     ns = len(s2)
     if (s2[0] != 'end') and (ns == 3)  :      
       # print "Line - Init. Con. =",k,s2   
       ic_t.append(float(s2[0]))          
       ic_r.append(float(s2[1]))
       ic_z.append(float(s2[2]))
       sl = ""
       for si in range(0,len(s2)) :
         sl = sl + str(s2[si]) + ","
       sl = sl + "\n"  
       lf.write(sl)       
     k=k+1   
   rf.close()   
   return  ic_r,ic_t,ic_z

def read_param(ipname) :
   iparam,tparam=[],[] # tparam = title,iparam=parameter
   print "Start Reading Parameter from : read_param %s " %ipname  
   ic_t,ic_r,ic_z=[],[],[] # initial condition data 
   rf=open(ipname,"r")   
   s = rf.read()
   no_lines=s.count('\n')
   print (" number of lines : Parameters ",no_lines)
   rf.seek(0) # move to first line
   k=1
   while k<= no_lines : 
     s1= rf.readline().strip('\n')
     s2=s1.split(',')
     print "k=",k,s1,s2     
     tparam.append(s2[0])
     s3=s2[1].strip()   # remove left & right blank in the string
     s4=s3.replace(" ","")  # remove blank in the string 
     iparam.append(s4)
     print tparam[k-1],iparam[k-1]
     k=k+1   
   rf.close()   
   print iparam
   return  tparam,iparam

def cal_xyz_from_rtz(ds_r,ds_t,ds_z,ic_r,ic_t,ic_z,lf) :
   print "Converting X,Y,Z from R,Theta,Z : cal_xyz_from_rtz "   
# Convert from R,Theta,Z + Conditioner to X,Y,Z Data for generating Interpolate curve of coil 
   s_x,s_y,s_z,s_r,s_t=[],[],[],[],[]
   print "Len of ds_r",len(ds_r),len(ds_z),len(ic_r)
   for i in range(0,len(ds_z)) : 
      theta = float(ds_t[i])*(2.0*3.14159265359)%(2.0*3.1414159265359) 
      new_x = cos(theta)*(ic_r[i]+ds_r[i])
      new_y = sin(theta)*(ic_r[i]+ds_r[i])
      new_z = ic_z[i]+ds_z[i]
      new_r = ds_r[i]+ic_r[i]
      new_theta=ds_t[i]    #  Theta --> Turn     
      s_x.append(new_x)
      s_y.append(new_y)
      s_z.append(new_z)
      s_t.append(new_theta)
      s_r.append(new_r)
      # print "****",new_x,new_y,new_z,"theta",theta,"new theta",new_theta

   print "Complete Generating new X,Y,Z with Initial Conditioner + Error Adjust "    
   return s_x,s_y,s_z,s_r,s_t   
            
def create_spring_r1(s_x,s_y,s_z,s_r,s_t,s_d) :
    print " Create Spring - R1 Method "
# ndiv is used in this function but ndiv will be not used in later
    ndiv = py_get_int("ndiv")
    py_send("*clear_geometry *system_reset")
    py_send("*set_curve_max_depth 15 ")
    py_send("*set_curve_type interpolate  *add_curves")  # interpolate curve generate
    for i in range (0,len(s_x)) :
      py_send("point(%f,%f,%f) \n" %(s_x[i],s_y[i],s_z[i]))
      #print " point id =",i,"radius =",s_r[i],"Theta =",s_t[i]
    py_send("# \n")  
    py_send("*sweep_all *remove_unused_points ")
    i_pts = py_get_int('npoints()')
    py_send("*set_curve_type tangent ")
    py_send("*add_curves 1 10.0 ")
    o_id = py_get_int("1")
    #print "Origin id ", o_id
    o_x,o_y,o_z=py_get_float("point_x(%d)"%o_id),py_get_float("point_y(%d)"%o_id),py_get_float("point_z(%d)"%o_id)
    print o_x,o_y,o_z
    u_x,u_y,u_z=py_get_float("point_x(%d)"%i_pts),py_get_float("point_y(%d)"%i_pts),py_get_float("point_z(%d)"%i_pts)
    v_x,v_y,v_z=py_get_float("point_x(1)"),py_get_float("point_y(1)"),py_get_float("point_z(%d)"%i_pts)
    py_send("*system_align ")
    py_send(" %f , %f , %f " %(o_x,o_y,o_z))
    py_send(" %f , %f , %f " %(u_x,u_y,u_z))
    py_send(" %f , %f , %f " %(v_x,v_y,v_z))
    create_sec_mesh_q1(s_d)
    py_send("*expand_reset *set_expand_repetitions %d " %ndiv)
    py_send("*expand_elements_curve 1 all_existing ")
    py_send("*elements_solid *regenerate *fill_view")
    #--- Point Add   ---
    py_send("*system_reset *add_points ")
    for i in range (0,len(s_x)) :
      py_send("%f %f  %f \n" %(s_x[i],s_y[i],s_z[i]))
    return

def create_spring_r2(s_x,s_y,s_z,s_r,s_t,s_d,lf) :
    sl = "\n  *** Start Generating Spring Model with the below Data : Create Spring Routine \n\n" 
    lf.write(sl)
    print " Create Spring - R2 Method "
#   Define the coil data sectioning information
    a_div = py_get_float("adiv")
    print " Angular division no. for a turn", a_div 
#   User will define the division of angular of Coil in this model division is 48 for a turn
    t_div = py_get_int("tdiv")
    c_turn = py_get_float("c_turn") # Total Turn No.
# Create Spring with divided interpolate curve
    py_send("*clear_geometry *system_reset")
    py_send("*set_curve_max_depth 15 ")

    # main curve generate with interpolate method


    py_send("*set_curve_type interpolate  ")  
    py_send("*add_curves ")
    for i in range (0,len(s_x)) :
      py_send("point(%f,%f,%f) \n" %(s_x[i],s_y[i],s_z[i]))
      print " point id =",i,"x,y,z =",s_x[i],s_y[i],s_z[i]
      sl=str(s_x[i])+","
      sl=sl+str(s_y[i])+","
      sl=sl+str(s_z[i])+","
      sl=sl+str(s_r[i])+","
      sl=sl+str(s_t[i]) + "\n"
      lf.write(sl)
    py_send("# \n")  
    py_send("*sweep_all *remove_unused_points ")

    # py_send("*save_as_model tmp_spring_curve.mud yes ")
        
    # End Section Curve Generate 
    i_pts = py_get_int('npoints()')
    py_send("*set_curve_type tangent ")
    py_send("*add_curves 1 10.0 ")
    o_id = py_get_int("1")
    #print "Origin id ", o_id
    o_x,o_y,o_z=py_get_float("point_x(%d)"%o_id),py_get_float("point_y(%d)"%o_id),py_get_float("point_z(%d)"%o_id)
    print o_x,o_y,o_z
    u_x,u_y,u_z=py_get_float("point_x(%d)"%i_pts),py_get_float("point_y(%d)"%i_pts),py_get_float("point_z(%d)"%i_pts)
    v_x,v_y,v_z=py_get_float("point_x(1)"),py_get_float("point_y(1)"),py_get_float("point_z(%d)"%i_pts)
    py_send("*system_align ")
    py_send(" %f , %f , %f " %(o_x,o_y,o_z))
    py_send(" %f , %f , %f " %(u_x,u_y,u_z))
    py_send(" %f , %f , %f " %(v_x,v_y,v_z))
    # Create Section Mesh
    create_sec_mesh_q1(s_d)
    t_cnt_elem=0   # reset total center elements no.
    dt_top,dt_bot = 0.5,0.5 # Define the alignment angle (0.5 = 180 deg.)  
    dt_top_cut = int(dt_top/a_div)
    dt_bot_cut = int(dt_bot/a_div)
    print " The No. of angular divisions of Coil cutting ", dt_top_cut, dt_bot_cut 
    s_margin = 1.0
    # Create a alignment cut surface      
    py_send("*system_reset ")
    py_send("*add_surface ")
    py_send("point(%f,%f,%f) " %((s_x[0]-(s_d+s_margin )),s_y[0],(s_z[0]-(s_d+s_margin ))) )   
    py_send("point(%f,%f,%f) " %((s_x[0]+(s_d+s_margin )),s_y[0],(s_z[0]-(s_d+s_margin ))) )   
    py_send("point(%f,%f,%f) " %((s_x[0]+(s_d+s_margin )),s_y[0],(s_z[0]+(s_d+s_margin ))) )   
    py_send("point(%f,%f,%f) " %((s_x[0]-(s_d+s_margin )),s_y[0],(s_z[0]+(s_d+s_margin ))) )           
    # Duplicate the cut surface for top alignment 
    py_send("*duplicate_reset ")
    py_send("*set_duplicate_rotations 0.0,0.0,%f " %(dt_top*360.0))
    py_send("*set_duplicate_translations 0.0,0.0,%f " %s_z[dt_top_cut])
    py_send("*duplicate_surfaces 1 # ")
    py_send("*duplicate_reset ")
    # Duplicate the cut surface for top alignment 
    py_send("*set_duplicate_rotations 0.0,0.0,%f " %(s_t[-dt_bot_cut-1]*360.0))
    py_send("*set_duplicate_translations 0.0,0.0,%f " %s_z[-dt_bot_cut-1])
    py_send("*duplicate_surfaces 1 # ")
    py_send("*intersect_curves_surface 2  1 #")
    py_send("*intersect_curves_surface 3  7 #")  
    # Expand section elem. along the curve  
    py_send("*select_elements_class quad4 ") # Select section elem.
    
    # py_send("*save_as_model tmp_sect.mud yes ")

    # Expand Section Element & Center Node
    py_send("*expand_reset *expand_shift ")
    tqt_div = (t_div*dt_top)
    py_send("*set_expand_repetitions %d " %tqt_div)
    py_send("*expand_elements_curve  6 all_selected ")
    py_send("*define tqt_div %d "%tqt_div) # Top Quarter total division no.
    t_cnt_elem=t_cnt_elem + tqt_div

    ct_div = ((c_turn-(dt_top+dt_bot))*t_div)
    py_send("*set_expand_repetitions %d " %ct_div)
    py_send("*expand_elements_curve  8 all_selected ")
    t_cnt_elem=t_cnt_elem + ct_div
    py_send("*define ct_div %d "%ct_div) # Center total division no.

    bqt_div = (t_div*dt_bot)
    py_send("*expand_remove *set_expand_repetitions %d " %bqt_div)
    py_send("*expand_elements_curve  9 all_selected ")
    t_cnt_elem=t_cnt_elem + bqt_div 
    py_send("*define bqt_div %d "%bqt_div) # Bottom Quarter total division no.

    py_send("*elements_solid *regenerate *fill_view")
    py_send("*remove_surfaces all_existing ")
    py_send("*define t_cnt_elem %d " %t_cnt_elem )
    return

def create_spring_r3(s_x,s_y,s_z,s_r,s_t,s_d,ndiv) :
    print " Create Spring - R3 Method "
# Create Spring with individual interpolate curve  : No good result
    py_send("*clear_geometry *system_reset")
    py_send("*set_curve_max_depth 15 ")
    py_send("*set_curve_type interpolate  *add_curves")  # interpolate curve generate
    for i in range (0,len(s_x)) :
      py_send("point(%f,%f,%f) \n" %(s_x[i],s_y[i],s_z[i]))
      #print " point id =",i,"radius =",s_r[i],"Theta =",s_t[i]
    py_send("# \n")  
    py_send("*sweep_all *remove_unused_points ")
    i_pts = py_get_int('npoints()')
    py_send("*set_curve_type tangent ")
    py_send("*add_curves 1 10.0 ")
    o_id = py_get_int("1")
    #print "Origin id ", o_id
    o_x,o_y,o_z=py_get_float("point_x(%d)"%o_id),py_get_float("point_y(%d)"%o_id),py_get_float("point_z(%d)"%o_id)
    print o_x,o_y,o_z
    u_x,u_y,u_z=py_get_float("point_x(%d)"%i_pts),py_get_float("point_y(%d)"%i_pts),py_get_float("point_z(%d)"%i_pts)
    v_x,v_y,v_z=py_get_float("point_x(1)"),py_get_float("point_y(1)"),py_get_float("point_z(%d)"%i_pts)
    py_send("*system_align ")
    py_send(" %f , %f , %f " %(o_x,o_y,o_z))
    py_send(" %f , %f , %f " %(u_x,u_y,u_z))
    py_send(" %f , %f , %f " %(v_x,v_y,v_z))
    create_sec_mesh_q1(s_d)
    py_send("*system_reset *system_cylindrical *set_curve_type interpolate")
    a_div = py_get_float("adiv") # angle division for each theta
    pi=180.0 # degree of angle    
#  Expand Section Model 
    for i in range (0,(len(s_t)-1)) :
#    for i in range (0,9) :
      i_crvs,max_crv_id=py_get_int('ncurves()'),py_get_int('max_curve_id()')
      t_i,t_i1=(float(s_t[i])*(2.0*pi)%(2.0*pi)), (float(s_t[i+1])*(2.0*pi)%(2.0*pi))
      if (abs(t_i1-0.0001) < 0.1) :
          t_i1 = 360.0
      print " R_i, Theta_i(deg.), Z_i ", s_r[i],t_i,s_z[i]
      print " R_i+1, Theta_i+1(deg.), Z_i+1 ", s_r[i+1],t_i1,s_z[i+1]
      dr,dt,dz=(s_r[i+1]-s_r[i])/a_div,(t_i1-t_i)/a_div,(s_z[i+1]-s_z[i])/a_div
      print i,"th Calculated dr, dt(deg.), dz ", dr,dt,dz," with angle division ",a_div 
      py_send("*add_curves ")  # interpolate curve generate for each points
      for j in range (0,a_div+1) :
        pt_r,pt_t,pt_z=(s_r[i]+dr*j),(t_i+dt*j),(s_z[i]+dz*j)
        py_send("point(%f,%f,%f) \n" %(pt_r,pt_t,pt_z))
        print "point(%f,%f,%f) " %(pt_r,pt_t,pt_z)
        #print " point id =",i,"radius =",s_r[i],"Theta =",t_i
      py_send("# \n")  
      py_send("*sweep_all *remove_unused_points ")      
      py_send("*expand_reset *expand_shift *set_expand_repetitions %d" %a_div)
      py_send("*expand_elements_curve %d section_el " %(max_crv_id+1))
    py_send("*elements_solid *regenerate *fill_view")    
    return

def create_seat(s_x,s_y,s_z,s_d) :
   print " Create Seat "
   i_crvs,max_crv_id=py_get_int('ncurves()'),py_get_int('max_curve_id()')
   #py_send("*show_view 2 *fill_view *merge_models seat_tl.mud ") 
   # Seat generation function should be updated later 2017.07.06
   s_h = max(s_z)
   py_send("*eval s_h %f " %s_h)
   py_send("*eval wire_dia %f " %s_d)   
   #find radius 
   r=[]
   for i in range (0,len(s_x)) :
      rad = sqrt(s_x[i]**2 + s_y[i]**2)
      r.append(rad)
   len_r=len(r)   
   #U_seat_Rad = min(r[0:7])-1.0
   #L_seat_Rad = min(r[len_r-7 : len_r]) -5.0
   U_seat_Rad = min(r[0:7])
   L_seat_Rad = min(r[len_r-7 : len_r])
   print " Seat Parameters ",s_h,s_d,len_r,U_seat_Rad,L_seat_Rad
   py_send("*define coil_h %f" %s_h)
   #py_send("*define s_d %f" %s_d)
   py_send("*define U_seat_Rad %f" %U_seat_Rad)
   py_send("*define L_seat_Rad %f" %L_seat_Rad)
   py_send("*select_clear *visible_selected ")   
   py_send("*system_reset *system_rotate 90 0 0 ")
   py_send("*set_curve_type line *add_curves ")
   py_send("point(170.0/2.0,-wire_dia/2.0-10.0,0.0) point(170/2.0,-wire_dia/2.0,0.0) ")
   py_send("point(170.0/2.0,-wire_dia/2.0,0.0) point(U_seat_Rad-wire_dia/2.0,-wire_dia/2.0,0.0) ")
   py_send("point(U_seat_Rad-wire_dia/2.0,-wire_dia/2.0,0.0) point(U_seat_Rad-wire_dia/2.0,-wire_dia/2.0+60.0,0.0) ")
   py_send("point(U_seat_Rad/2.0-wire_dia/2.0,-wire_dia/2.0+60.0,0.0) point(0.0,-wire_dia/2.0+60.0,0.0) ")
   py_send("*set_curve_type fillet *add_curves %d %d  10.0 " %(max_crv_id+3,max_crv_id+4) )
   py_send("*revolve_curves all_visible ")
   py_send("*store_surfaces Upper_seat all_visible")
   py_send("*select_clear *visible_selected ")
   py_send("*flip_surfaces 6 # ")   
   py_send("*set_curve_type line *add_curves ")
   py_send("point(170.0/2.0,s_h+wire_dia/2.0+10.0,0.0) point(170.0/2.0,s_h+wire_dia/2.0,0.0) ")
   py_send("point(170.0/2.0,s_h+wire_dia/2.0,0.0)   point(L_seat_Rad-wire_dia/2.0,s_h+wire_dia/2.0,0.0) ")
   py_send("point(L_seat_Rad-wire_dia/2.0,s_h+wire_dia/2.0,0.0) point(L_seat_Rad-wire_dia/2.0,s_h+wire_dia/2.0-60.0,0.0) ")
   py_send("point(L_seat_Rad-wire_dia/2.0,s_h+wire_dia/2.0-60.0,0.0) point(0.0,s_h+wire_dia/2.0-60.0,0.0) ")
   #py_send("*set_curve_type fillet *add_curves  15 16 10.0 ")
   py_send("*set_curve_type fillet *add_curves %d %d  10.0 " %(max_crv_id+10,max_crv_id+11) )   
   py_send("*revolve_curves all_visible ")
   py_send("*store_surfaces Lower_seat all_visible ") 
   py_send("*flip_surfaces all_visible ")
   py_send("*select_clear *invisible_selected ")
   py_send("*set_sweep_tolerance 0.01 *sweep_all ")
   py_send("*system_reset *show_view 2 ")
   py_send("*fill_view ")
   py_send("*undefine s_h ")
   return

def create_sec_mesh_q1(s_d) :
    print " Create Section Mesh - q1(Adv. Front) Method "    
    py_send("*set_curve_type arc_cpp *add_curves ")  # Curve 3
    py_send(" 0.0 0.0 0.0 ")
    py_send(" %f 0.0,0.0 \n" %(float(s_d)/2.0))
    py_send(" 0.0, %f , 0.0 \n" %(float(s_d)/2.0))  
    py_send("*set_curve_type line *add_curves ")  
    py_send("point(0.0,0.0,0.0) \n")  # Curve 4
    py_send("point(%f,0.0,0.0)\n" %(float(s_d)/2.0))
    py_send("point(0.0,0.0,0.0) \n")  # Curve 5
    py_send("point(0.0,%f,0.0)\n" %(float(s_d)/2.0))
    py_send("*sweep_all ")
    py_send("*set_curve_div_type_fix_ndiv *set_curve_div_num 4 ")
    py_send("*apply_curve_divisions  3 # ")
    py_send("*set_curve_div_type_fix_ndiv *set_curve_div_num 3 ")
    py_send("*apply_curve_divisions  4 5 # ")    
    py_send("*af_planar_quadmesh  3 4 5 # ")
    py_send("*detach_nodes all_existing *detach_edges all_existing *detach_faces all_existing ")
    py_send("*duplicate_reset *set_duplicate_repetitions 3 ")
    py_send("*set_duplicate_rotation z 90 ")
    py_send("*duplicate_elements all_existing *sweep_all")
    py_send("*move_reset *set_move_rotations 0 90 0 ")
    py_send("*move_elements all_existing ")
    py_send("*store_elements section_el all_existing ")     
    return

def create_sec_mesh_q2(s_d) :    
    print " Create Section Mesh - q2(Adv. Front) Method "    
    py_send("*set_curve_type arc_cpp *add_curves ")  # Curve 3
    py_send(" 0.0 0.0 0.0 ")
    py_send(" %f 0.0,0.0 \n" %(float(s_d)/2.0))
    py_send(" 0.0, %f , 0.0 \n" %(float(s_d)/2.0))  
    py_send("*set_curve_type line *add_curves ")  
    py_send("point(0.0,0.0,0.0) \n")  # Curve 4
    py_send("point(%f,0.0,0.0)\n" %(float(s_d)/2.0))
    py_send("point(0.0,0.0,0.0) \n")  # Curve 5
    py_send("point(0.0,%f,0.0)\n" %(float(s_d)/2.0))
    py_send("*sweep_all ")
    py_send("*set_curve_div_type_fix_ndiv *set_curve_div_num 2 ")
    py_send("*apply_curve_divisions  3 4 5 # ")
    py_send("*af_planar_quadmesh  3 4 5 # ")
    py_send("*subdivide_reset *sub_divisions 2 2 2 ")
    py_send("*subdivide_elements all_existing    ")
    py_send("*detach_nodes all_existing *detach_edges all_existing *detach_faces all_existing ")
    py_send("*duplicate_reset *set_duplicate_repetitions 3 ")
    py_send("*set_duplicate_rotation z 90 ")
    py_send("*duplicate_elements all_existing *sweep_all")
    py_send("*move_reset *set_move_rotations 0 90 0 ")
    py_send("*move_elements all_existing ")
    py_send("*store_elements section_el all_existing ")     
    return
   
def create_sec_mesh_a(s_d) :    
    print " Create Section Mesh - a(Adv. Front) Method "    
    py_send("*set_curve_type circle_cr *add_curves ")
    py_send(" 0.0 0.0 0.0 ")
    py_send(" %f " %(float(s_d)/2.0))
    py_send("*set_curve_div_type_fix_ndiv *set_curve_div_num 36 ")
    py_send("*apply_curve_divisions  3 # ")
    py_send("*af_planar_quadmesh  3 # ")
    py_send("*detach_nodes all_existing *detach_edges all_existing *detach_faces all_existing ")
    py_send("*move_reset *set_move_rotations 0 90 0 ")
    py_send("*move_elements all_existing ")
    py_send("*store_elements section_el all_existing ")     
    return
    
def create_center_line_elem(ndiv,s_d,cnt_beam_start) :    
    print " Create Center Line Beam Elem. :  create_center_line_elem "    
    py_send("*duplicate_reset *duplicate_curves 1 # ")
    py_send("*select_curves  1 # *visible_selected ")
    py_send("*sub_divisions %d 1 1 " %ndiv)
    py_send("*subdivide_curves_real 1 # ")
    py_send("@set($convert_entities,curves) *set_convert_uvdiv u 1")
    py_send("*convert_curves all_visible ")
    py_send("*set_sweep_tolerance 0.01 *sweep_all  ")
#    py_send("*sweep_nodes all_visible ")
#    py_send("*sweep_curves all_visible ")
    print "Starting Beam from no. of %d  " %cnt_beam_start
    py_send("*prog_param renumber:start %d" %cnt_beam_start)
    py_send("*renumber_node_list all_visible ")
    py_send("*renumber_element_list all_visible ")
    py_send("*store_elements spring_center all_visible ")
    py_send("*store_elements spring_solid all_invisible ")
    py_send("*new_geometry *geometry_type mech_three_beam_ela @popdown(geom_new_popmenu) @popdown(geom_new_structural_pm) ")
    py_send("*geometry_name spring_center_dummy_beam ")    
    py_send("*geometry_param diameter %f " %s_d/10 )
    py_send("*geometry_vector orientx 0 0 1 ")
    py_send("*geometry_param cont_radius %f " %s_d )
    py_send("*add_geometry_elements spring_center ")
    py_send("*invisible_selected")
    return

def create_center_line_elem_r2(s_d,cnt_beam_start) :   
    print " Create Center Line Elem. R2 : create_center_line_elem_r2 "
#  
#  Division of Curve should be defined as the function of angle division number.
#
    turns=py_get_int("coil_turn_no")
    t_div=py_get_int("tdiv")
    n_div=py_get_int("ndiv")
    tqt_div = py_get_int("tqt_div") # Top Quarter Div.
    ct_div = py_get_int("ct_div") # Top Quarter Div.
    bqt_div = py_get_int("bqt_div") # Top Quarter Div.    
    a_div = round(1/py_get_float("adiv"))
    # Tempoerarily save a file
    # py_send("*save_as_model tmp1.mud yes ")
    py_send("*duplicate_reset *duplicate_curves 6 8 9 # ")
    py_send("*select_curves  6 8 9 # *visible_selected ")
    py_send("*sub_divisions %d 1 1 "%tqt_div )
    py_send("*subdivide_curves_real 6 # ") 
    py_send("*sub_divisions %d 1 1 "%ct_div )
    py_send("*subdivide_curves_real 8 # ") 
    py_send("*sub_divisions %d 1 1 "%bqt_div )
    py_send("*subdivide_curves_real 9 # ") 


    py_send("@set($convert_entities,curves) *set_convert_uvdiv u 1")
    py_send("*convert_curves all_visible ")
    py_send("*set_sweep_tolerance 0.01 *sweep_all  ")
    print "Starting Beam %d  ==" %cnt_beam_start
    py_send("*prog_param renumber:start %d" %cnt_beam_start)
    py_send("*renumber_node_list all_visible ")
    py_send("*renumber_element_list all_visible ")
    py_send("*store_elements spring_center all_visible ")
    py_send("*store_elements spring_solid all_invisible ")
    py_send("*new_geometry *geometry_type mech_three_beam_ela @popdown(geom_new_popmenu) @popdown(geom_new_structural_pm) ")
    py_send("*geometry_name spring_center_dummy_beam ")    
    py_send("*geometry_param diameter %f " %s_d )
    py_send("*geometry_vector orientx 0 0 1 ")
    py_send("*geometry_param cont_radius %f " %s_d )
    py_send("*add_geometry_elements spring_center ")
    py_send("*invisible_selected")
    return

    
def add_material(s_name,i_no) :
    print "Adding Materials :  add_material "
#
# In order to select material databse in program, 
# program should change the material name base on the GUI input
# For example : *define material_db ****.mud
#
#    py_send("*mater_read posh1s125.mud ")
# It should be checked later that material read does not wrk in python
    mat_name=py_get_string("mat_name")
    mat_file=mat_name
    py_send("*set_merge_renumber off ")
    py_send("*merge_models %s *save_as_model %s  yes" %(mat_file,(s_name+"_"+str(i_no))))
    py_send("*add_mater_elements spring_solid ")
    py_send("*copy_mater *mater_name Center_dyummy_mat ")
    py_send("*mater_param structural:youngs_modulus 0.01 ")
    py_send("*mater_option structural:plasticity:off *add_mater_elements spring_center ")
    return

def create_contact(stroke) :
    print " Create Contact : create_contact " 
    py_send("*new_cbody mesh *contact_option state:solid *contact_option skip_structural:off ")
    py_send("*contact_body_name spring *add_contact_body_elements spring_solid ")
    py_send("*new_cbody geometry *contact_option geometry_nodes:off " )
    py_send("*contact_body_name Upper_seat *add_contact_body_surfaces Upper_seat ")
    py_send("*new_cbody geometry *contact_option geometry_nodes:off ")
    py_send("*contact_body_name Lower_Seat *add_contact_body_surfaces Lower_seat ")
    py_send("*new_md_table 1 1 *set_md_table_type 1 time *table_name Seat_move ")
    py_send("*table_add 0 1 1 1 1 -1 2 -1 2 0 3 0 *table_fit")
    py_send("*edit_contact_body Lower_seat *cbody_param_table vz Seat_move ")
    py_send("*contact_value vz %f " %(-1*stroke))    
    py_send("*new_interact mesh:mesh *interact_option state_1:solid *interact_option state_2:solid ")
    py_send("*interact_name Fr_0.1 *interact_param friction 0.1 ")
    py_send("*new_interact mesh:geometry *interact_option state_1:solid ")
    py_send("*interact_name Fr_0.1_seat *interact_param friction 0.1 ")
    py_send("*new_contact_table ")
    py_send("*ctable_entry spring spring *contact_table_option spring spring contact:on" )
    py_send("*prog_string ctable:old_interact Fr_0.1 *ctable_entry_interact spring spring ")
    py_send("*ctable_entry spring Upper_seat *contact_table_option spring Upper_seat contact:on ")
    py_send("*prog_string ctable:old_interact Fr_0.1_seat *ctable_entry_interact spring Upper_seat ")
    py_send("*ctable_entry spring Lower_Seat *contact_table_option spring Lower_Seat contact:on ")
    py_send("*prog_string ctable:old_interact Fr_0.1_seat *ctable_entry_interact spring Lower_Seat ")
    return

def add_conditions(cnt_beam_start) :
    print " Adding Initial & Boundary Condition : add_conditions"
    py_send("*new_icond *icond_type state_variable @popdown(icond_new_pm) ")
    py_send("*icond_name hset_temp ")
    py_send("*icond_dof var *icond_dof_value var *icond_dof_value var 400 ")
    py_send("*add_icond_elements all_existing " )
    py_send("*new_apply *apply_type gravity_load @popdown(apply_new_pm) ")
    py_send("*apply_name gravity ")
    py_send("*apply_dof z *apply_dof_value z -9800 ")
    py_send("*add_apply_elements spring_solid ")
    py_send("*new_apply *apply_type fixed_displacement @popdown(apply_new_pm) ")
    py_send("*apply_name End_Fix_Y ")
    py_send("*apply_dof y *apply_dof_value y 0.0 ")
    py_send("*add_apply_nodes %d #" %cnt_beam_start )       
    return
    
def create_lcase_job() :
    print " Create Loadcase & Job : create_lcase_job "
    py_send("*new_loadcase *loadcase_type struc:dyn_trans ")
    py_send("*loadcase_name setting ")
    py_send("*loadcase_ctable ctable1 ")
    py_send("*loadcase_value nsteps 50 ")
    py_send("*loadcase_value dyn_contact_pro_fact 0.5 ")
    py_send("*loadcase_value maxrec 30 ")
    py_send("*copy_loadcase *loadcase_name return ")
    py_send("*loadcase_value nsteps 30 ")
    py_send("*loadcase_option error:relabs")
    py_send("*loadcase_value force 0.1 ")
    py_send("*loadcase_value minforce  0.01 ")
    py_send("*loadcase_value maxforce 0.01 ")
    py_send("*prog_use_current_job on *new_job *job_class structural ")
    py_send("*add_job_loadcases setting *add_job_loadcases return ")
    py_send("*job_option strain:large *add_post_tensor stress *add_post_tensor strain *add_post_tensor el_strain *add_post_tensor pl_strain ")
    py_send("*job_contact_table ctable1 ")
    py_send("*job_option frictype:coulomb_bilinear ")
    py_send("*element_type 7 spring_solid ")
    py_send("*element_type 98 spring_center ")
    py_send("*save_model ")    
    return


#   Calculate Angle Counter Clock wise 
def length(v):
    return sqrt(v[0]**2+v[1]**2)
def dot_product(v,w):
   return v[0]*w[0]+v[1]*w[1]
def determinant(v,w):
   return v[0]*w[1]-v[1]*w[0]
def inner_angle(v,w):
   cosx=dot_product(v,w)/(length(v)*length(w))
   rad=acos(cosx) # in radians
   return rad*180/pi # returns degrees
def angle_clockwise(A, B):
    inner=inner_angle(A,B)
    det = determinant(A,B)
    if det<0: #this is a property of the det. If the det < 0 then B is clockwise of A
        return inner
    else: # if the det > 0 then A is immediately clockwise of B
        return 360-inner

def cal_rtz(nstart,fname) :
#
#  This routine calculate r,t,z from result(rezoned file)
#  Actually result is saved after center aligning
#
    oname=fname+"_cal_rtz.csv"
    wf=open(oname,"w")
    s= "Node ID,X,Y,Z,R, Theta(Deg.),Theta(turn.), \n"
    wf.write(s)
    print "Calculate R,T,Z from result file %s : cal_rtz" %fname
    #n_div = py_get_int("ndiv") 
    t_cnt_elem = py_get_int("t_cnt_elem")
    py_send("*open_model %s " %fname)
    print " Open Result File to export R,T,Z Data :  ", fname
    ox,oy,oz=0.0,0.0,0.0
#   Get Nodal Coordinate of Center Beam Element
    n_x,n_y,n_z = [],[],[]
    n_id,n_arg=[],[]
    r,theta,z=[],[],[]
    nnodes=py_get_int("nnodes()")
    for i in range(0,t_cnt_elem+1) :
      n_id.append(nstart+i)
    for  j in range(0,nnodes) :
      n_arg.append(py_get_int("node_id(%s) " %str(j+1)))
    #print n_arg
    turns=0
    theta_deg_p=0.0
    for k in range(0,t_cnt_elem+1) :  
       b = [item for item in range(len(n_arg)) if n_arg[item] == n_id[k]]
       no_id=n_arg[b[0]]
       c_x=py_get_float("node_x(%s)" %no_id)
       c_y=py_get_float("node_y(%s)" %no_id)
       c_z=py_get_float("node_z(%s)" %no_id)
       n_x.append(c_x),n_y.append(c_y),n_z.append(c_z)
#  Calculate Radius, Theta, Z
       rad=sqrt((c_x - ox)**2 + (c_y - oy)**2) 
       vec_a=[c_x,c_y]
       vec_b=[1.0,0.0]
       #print "vector =",vec_a,vec_b
       theta_deg=angle_clockwise(vec_a,vec_b)
       theta_turn = theta_deg/360.0 + turns
       if k == 0 :
         turns = 0         
         theta_turn = 0.0
         theta_deg = 0.0
       if k >= 1 and (theta_deg - theta_deg_p) < 0.0 :
          turns = turns +1
          print "%s turn no is increased @ %d" %(fname,k), turns
          theta_turn = (theta_deg)/360.0 + turns 
       s=str(no_id)+","+str(c_x)+","+str(c_y)+","+str(c_z)+","   
       s=s+str(rad)+","+str(theta_deg)+","+str(theta_turn)+",\n"
       wf.write(s)
       r.append(rad),theta.append(theta_turn),z.append(c_z)       
       theta_deg_p = theta_deg
    #print "No. Element in List n_x,n_y,n_z", len(n_x),len(n_y),len(n_z)
    #print "No. Element in List r,theta,z", len(r),len(theta),len(z)
    wf.close()
    return  r,theta,z 

def export_rtz_file(r,t,z,fname) :
    oname = fname+"_rtz.csv"
    oname2 = fname+"_rtz_detail.csv"
    print "Export R,T,Z to %s : export_rtz_file" %oname,"\n Lenght of file ",len(r),len(t),len(z)
#  exp_r,exp_t,exp_z : Exported R,T,Z Data with 1/8 Division
    a_div = py_get_float("adiv") # Anle Division
    print "a_div =",a_div
    exp_r,exp_t,exp_z = [],[],[]
    wf=open(oname,"w") 
    wf2=open(oname2,"w") 
    # print the R,Theta,Z Data of Center Beam Elements 
    #for i in range(0,len(r)) :  
    #   print "%s : Radius, theta, z "%fname,i,r[i],t[i],z[i]
    s="Radius,Theta(Turn),Z,\n"
    wf.write(s)
    wf2.write(s)
    for i in range(0,len(r)) :
      wflag=0
      tc=t[i]-t[0]
      #print "Theta ",i,t[i],tc,tc%a_div
      s= str(r[i])+","+str(t[i])+","+str(z[i])+"\n"
      wf2.write(s)
      if (i==0) or (i==len(r)-1) or (tc%a_div <= 0.00001):
         s= str(r[i])+","+str(tc)+","+str(z[i]-z[0])+"\n"
         wflag=1
         exp_r.append(r[i]), exp_t.append(tc), exp_z.append(z[i]-z[0])
         wf.write(s)
      if wflag==0 :
         tcr=tc%a_div
         tp=t[i-1]-t[0]
         tpr=tp%a_div
         if tpr > tcr :
           ri=interpolate_2point(tp,r[i-1],tc,r[i],(tc-tcr))
           ti=tc-tcr
           zi=interpolate_2point(tp,z[i-1]-z[0],tc,z[i]-z[0],(tc-tcr))
           s= str(ri)+","+str(ti)+","+str(zi)+"\n"
           wflag=1
           exp_r.append(ri), exp_t.append(ti), exp_z.append(zi)           
           wf.write(s)
    wf.close()
    wf2.close()
    return exp_r,exp_t,exp_z

def interpolate_2point(x1,y1,x2,y2,xi) :
    slope = (y2-y1)/(x2-x1)
    yi=slope*(xi-x1)+y1
    return yi

def export_rtz_file_r2(r,t,z,fname) :
# This routine is dropped since caluculating r,t,z is not correct.
    oname = fname+"_rtz.csv"
    print "Export R,T,Z to %s : export_rtz_file" %oname
#  exp_r,exp_t,exp_z : Exported R,T,Z Data with 1/8 Division
    a_div = py_get_float("adiv") # Anle Division
    t_div = py_get_int("tdiv")
    n_div = py_get_int("ndiv")
    cnt_int= int(t_div/round(1.0/a_div))
    print "(export_rtz_file_r2) a_div =",a_div,"cnt_int=",cnt_int
    exp_r,exp_t,exp_z = [],[],[]
    wf=open(oname,"w") 
    # print the R,Theta,Z Data of Center Beam Elements 
    #for i in range(0,len(r)) :  
    #   print "%s : Radius, theta, z "%fname,i,r[i],t[i],z[i]
    s="Radius,Theta(Turn),Z,\n"
    wf.write(s)
    j=0
    for i in range(0,len(r),cnt_int) :
       t_n =j*a_div
       s= str(r[i])+","+str(t_n)+","+str(z[i])+"\n"
       if i == len(r)-1 :
        t_n=t[-1]
        s= str(r[i])+","+str(t_n)+","+str(z[i])+"\n"
       wf.write(s)
       exp_r.append(r[i]), exp_t.append(t_n), exp_z.append(z[i])
       print r[i],",",exp_r[-1],",",t_n,",",z[i] 
       j=j+1         
    wf.close()
    return exp_r,exp_t,exp_z

def cal_Err(r1,t1,z1,r0,t0,z0,status) :
    print "Calculate Error with comparing result & design : cal_Err"
    
    # r0,t0,z0 : Design Data 
    # r1,t1,z1 : Setting Result Data 
    e_r,e_t,e_z=[],[],[]
    #print r0
    print "Length of List",status,len(r1),len(t1),len(z1),len(r0),len(t0),len(z0)
    for i in range (0,len(r0)) :
       e_r.append(r0[i] - r1[i])
       e_t.append(t0[i] - t1[i])
       e_z.append(z0[i] - z1[i]) 
       #print "Z  :",z0[i] , z1[i]
       #print " Error(R,T,Z) : r0,t0,z0",status,i,r0[i],t0[i],z0[i] 
       #print " Error(R,T,Z) : r1,t1,z1",status,i,r1[i],t1[i],z1[i]                   
       print " Error(R,T,Z) : er,et,ez",status,i,e_r[i],e_t[i],e_z[i]                   
    return e_r,e_t,e_z

def write_spring_data(fname,header,x,y,z,r,t) :
    print "Write Spring Data with Design + Conditioner : write_spring_data"
# Write Design + Initial Conditioner Data
    sdf=open(fname,"w")
    sdl = header+"\n"
    sdf.write(sdl)
    sdl = " X , Y, Z, R, Theta, \n"
    sdf.write(sdl)
    for i in range(0,len(x)) :
       sdl = str(x[i])+","+str(y[i])+","+str(z[i])+","+str(r[i])+","+str(t[i])+",\n"
       sdf.write(sdl)
    sdf.close()
    return

def write_err_data(fname,header,z,r,t) :
    print "Write Error Data (R,Theta,Z) : write_err_data"
# Write Error Data Data
    sdf=open(fname,"w")
    sdl = header+"\n"
    sdf.write(sdl)
    sdl = "Theta, R, Z, \n"
    sdf.write(sdl)
    for i in range(0,len(t)) :
       sdl = str(t[i])+","+str(r[i])+","+str(z[i])+",\n"
       sdf.write(sdl)
    sdf.close()
    return

def submit_marc_run(sc_name) :
    print " Submit Marc : submit_marc_run"
    #fname=get_fname()
    py_send("*save_model yes ")
    py_send("*write_marc %s_job1.dat yes" %sc_name )
    py_send("*system_command C:\\MSC.Software\\Marc\\2014.2.0\\marc2014.2\\tools\\run_marc -j %s_job1 -b n -v n" %sc_name)
    return

def save_formed_coil(sc_name) :
    sc_fname = sc_name+"_formed_rezoned"
    print " Save Formed Coil : save_formed_coil %s" %sc_fname
    py_send("*set_deformed off ")
    py_send("*post_open %s_job1.t16 *post_skip_to 0 *post_rezone " %sc_name)
    py_send("*save_as_model %s yes " %sc_fname)
    py_send("*post_close ")   
    py_send("*open_model %s " %sc_fname)
    post_align_formed()
    sc_fname1 = sc_fname+"_align"
    py_send("*save_as_model %s yes " %sc_fname1)    

    return

def post_align_formed() :   
    print " Start Post Alignment : Formed " 
    nstart=py_get_int("cnt_beam_start")
    cnt_node_l = py_get_int("cnt_beam_start")+py_get_int("t_cnt_elem")+2
#   Traslation Alignment of Center 
    print " Center Low Node : ",cnt_node_l
    x,y,z=py_get_float("node_x(%d)" %cnt_node_l),py_get_float("node_y(%d)"%cnt_node_l),py_get_float("node_z(%d) "%nstart)
    print "X,Y,Z of Center Low ",x,y,z
    dx,dy,dz = (0.0-x),(0.0-y),(0.0-z)
    print " Movement of Formed Alignment ",dx,dy,dz
    py_send("*move_reset ")
    py_send("*set_move_translation x %f" %dx) 
    py_send("*set_move_translation y %f" %dy) 
    py_send("*set_move_translation z %f" %dz) # aligment is referred to starting cnt beam node
    py_send("*move_model ")
    
    x,y,z=py_get_float("node_x(%d)" %cnt_node_l),py_get_float("node_y(%d)"%cnt_node_l),py_get_float("node_z(%d) "%cnt_node_l)
    print "X,Y,Z of Center Low : Afte Alignment ",x,y,z
    return
    
def save_set_coil(sc_name) :
    sc_sname = sc_name+"_set_rezoned"   
    print " Save Set Coil : save_set_coil %s" %sc_sname
    py_send("*set_deformed off ")
    py_send("*post_open %s_job1.t16 *post_skip_to_last *post_rezone " %sc_name)   
    py_send("*save_as_model %s yes " %sc_sname)
    py_send("*post_close ")
    py_send("*open_model %s  " %sc_sname)
    post_align_set()
    sc_sname1 = sc_sname+"_align"
    py_send("*save_as_model %s yes " %sc_sname1)    
    return

def post_align_set() : 
    print " Start Post Alignment : Set"   
    py_send("*move_reset ")
    nstart=py_get_int("cnt_beam_start")
    cnt_node_l = py_get_int("cnt_beam_start")+py_get_int("t_cnt_elem")+2
#   Traslation Alignment of Center 
    x1,y1,z1=py_get_float("node_x(%d)" %cnt_node_l),py_get_float("node_y(%d)"%cnt_node_l),py_get_float("node_z(%d)" %nstart)
    dx1,dy1,dz1 =(0.0-x1),(0.0-y1),(0.0-z1)
    print " Center Low Node : ",cnt_node_l
    print "X,Y,Z of Center Low ",x1,y1,z1    
    print " Movement : dx1,dy1,dz1 =",dx1,dy1,dz1    
    py_send("*set_move_translation x %f" %dx1) 
    py_send("*set_move_translation y %f" %dy1) 
    py_send("*set_move_translation z 0.0") # aligment is referred to starting cnt beam node
    py_send("*move_model")
#   Rotation Alignment of Center 
    cnt_node_t = cnt_node_l+2
    x1,y1,z1=py_get_float("node_x(%d)" %cnt_node_l),py_get_float("node_y(%d)"%cnt_node_l),py_get_float("node_z(%d)" %cnt_node_l)    
    x2,y2,z2=py_get_float("node_x(%d)" %cnt_node_t),py_get_float("node_y(%d)"%cnt_node_t),py_get_float("node_z(%d)" %cnt_node_t)
    print "X,Y,Z of Center Low ",x1,y1,z1    
    print "X,Y,Z of Center Top ",x2,y2,z2        
    dx2,dy2,dz2=(x2-x1),(y2-y1),(z2-z1)
    print " dx2,dy2,dz2 =",dx2,dy2,dz2
    A,B=[dy2,dz2],[1.0,0.0]
    angle=angle_clockwise(A, B)
    print "Angle Y (Rx)=",90.0-angle
    py_send("*move_reset *set_move_point %f %f %f "%(x1,y1,z1))  
    py_send("*set_move_rotation x %f   *move_model" %(90.0-angle))
    A,B=[dz2,dx2],[1.0,0.0]
    angle=angle_clockwise(A, B)
    print "Angle Z (Ry)=",360.0-angle   
    py_send("*move_reset *set_move_point %f %f %f "%(x1,y1,z1))  
    py_send("*set_move_rotation y %f   *move_model" %(360.0-angle)) 
    x1,y1,z1=py_get_float("node_x(%d)" %cnt_node_l),py_get_float("node_y(%d)"%cnt_node_l),py_get_float("node_z(%d)" %cnt_node_l)    
    x2,y2,z2=py_get_float("node_x(%d)" %cnt_node_t),py_get_float("node_y(%d)"%cnt_node_t),py_get_float("node_z(%d)" %cnt_node_t)
    print "X,Y,Z of aligned Center Low ",x1,y1,z1    
    print "X,Y,Z of aligned Center Top ",x2,y2,z2           
    cnt_node_s = py_get_int("cnt_beam_start")
    x1,y1,z1=py_get_float("node_x(%d)" %cnt_node_l),py_get_float("node_y(%d)"%cnt_node_l),py_get_float("node_z(%d)" %cnt_node_l)    
    x3,y3,z3=py_get_float("node_x(%d)" %cnt_node_s),py_get_float("node_y(%d)"%cnt_node_s),py_get_float("node_z(%d)" %cnt_node_s) 
    dx3,dy3,dz3 =(x1-x3),(y1-y3),(z1-z3)
    print " dx3,dy3,dz3 =",dx3,dy3,dz3    
    A=[dx3,dy3]
    B=[1.0,0.0]
    angle=angle_clockwise(A, B)
    print "Angle RZ (Rz)=",180.0-angle
    py_send("*move_reset *set_move_rotation z %f   *move_model" %(180.0-angle))
    dzz=py_get_float("node_z(%d)" %nstart)
    print " dzz  ==",dzz
    py_send("*move_reset *set_move_translation z %f" %(-dzz)) # aligment is referred to starting cnt beam node
    py_send("*move_model")
    return

def main():  
   py_send("*new_model yes") 
   ipname="coil_param.csv"
   title,param=read_param(ipname)   
   iname=param[1] 
   icname=param[2]
   logname=param[3]
   lf=open(logname,"w")
   hstroke=float(param[4])
   py_send("*eval hset_stroke %f " %hstroke)
   hstemp =float(param[5])
   py_send("*eval hset_temp %f " %hstemp)
   cnt_beam_start= int(param[6])
   py_send("*eval cnt_beam_start %s " %cnt_beam_start)   
   err_tol = float(param[7])
   max_itr = int(param[8])
   mat_name = str(param[9])
   py_send("*define mat_name %s" %mat_name)   

   #  ---   Read Coil Data ---
   ds_x,ds_y,ds_z,ds_r,ds_t,coil_param=read_coil_data(iname,lf)
   total_theta=ds_t[-1]
   e_div = 6.0 # Number of division through each turn no. inc.
   py_send("*define e_div %f"%e_div)
   a_div = py_get_float("adiv")
   t_div = round(1.0/a_div*e_div) # 1.0/a_div* 
   n_div = round(total_theta,0)*t_div
   e_fac = 1.0 # error conditioner Factor -> Later controled 2017.07.05.
   print "a_div,t_div,n_div = ",a_div,t_div,n_div
   py_send("*eval tdiv %d" %t_div)
   py_send("*eval ndiv %d" %n_div)
   py_send("*eval efac %f" %e_fac)
   design_data=[ds_x,ds_y,ds_z,ds_r,ds_t]
   sdf_name,header=coil_param[0]+"_d_spring_data.csv"," Design Data  "
   write_spring_data(sdf_name,header,ds_x,ds_y,ds_z,ds_r,ds_t) 
   #  ---   Read Initial Conditioner Data ---     
   er0,et0,ez0=read_ic_data(icname,lf)
   ic=[er0,et0,ez0]
   max_err_z,max_err_r=max(ez0),max(er0)
   #print " Max. Error of Z, Radius : ",max_err_z,max_err_r
#  Start Iterative Method 
   i_no=1
   #while (max_err_z > err_tol) or (min_err_z < -1*err_tol) or (max_err_r > err_tol) or (min_err_r < -1*err_tol) or (i_no < max_itr)  :
   while (i_no <= max_itr)  : 
      sl = "\n Start %d - th Iteration of Setting Simulation : Marc Run \n\n" %i_no
      print sl
      lf.write(sl)
      sc_name=coil_param[0]+"_"+str(i_no)  # Define Current sim. model name
      sl="\n Simulation Model Name : "+sc_name+" \n\n"
      print sl
      lf.write(sl)
      py_send("*define sc_name %s " %sc_name )
      if i_no == 1 :
        # Convert from R,Theta,Z + Conditioner to X,Y,Z Data for generating Interpolate curve of coil 
         s_x,s_y,s_z,s_r,s_t=cal_xyz_from_rtz(ds_r,ds_t,ds_z,er0,et0,ez0,lf)
        # Write Prog. Used Error(Initial Conditioner) Data         
         sdf_name,header=sc_name+"_conditioner.csv",sc_name+" %d-th Conditioner Data  " %i_no   
         write_err_data(sdf_name,header,ez0,er0,et0) 
         print " Error List Length =",len(ez0),len(er0),len(et0)
        # Write New Spring Data ( Design + Initial Conditioner )
         sdf_name,header=sc_name+"_spring_data.csv"," Design Data + Initial Conditioner "
         write_spring_data(sdf_name,header,s_x,s_y,s_z,s_r,s_t)
         et_p,er_p,ez_p=et0,er0,ez0
      else :
         et_i,er_i,ez_i=[],[],[]
         # Calculate Updated Conditioner
         for j in range(0,len(er)) :
            et_i.append(et_p[j]+et[j]*e_fac)
            er_i.append(er_p[j]+er[j]*e_fac)
            ez_i.append(ez_p[j]+ez[j]*e_fac)
            #print "Updated error : ",j,et_i[j], er_i[j], ez_i[j]
         err_fname=sc_name+"_conditioner.csv"
         header=sc_name+" %d-th Conditioner Data  " %i_no
         write_err_data(err_fname,header,ez_i,er_i,et_i)
         #  Calculate New Spring Data with Error
         s_x,s_y,s_z,s_r,s_t=cal_xyz_from_rtz(ds_r,ds_t,ds_z,er_i,et_i,ez_i,lf)     
         et_p,er_p,ez_p=et_i,er_i,ez_i
      spring_data=[s_x,s_y,s_z,s_r,s_t,coil_param]
      py_send("*new_model yes") 
# Generate Coil Spring
      generate_spring(spring_data,hstroke,cnt_beam_start,i_no,lf)
      submit_marc_run(sc_name)
      py_send("*set_deformed off *set_showmag off ")  
      sl= "\n Start %d - th Iteration : Extract Result \n \n" %i_no
      print sl
      lf.write(sl)
# Save Formed Coil Model
      save_formed_coil(sc_name)
# Save Set Coil Model  
      save_set_coil(sc_name) 
# Calculate Error From the previous result
#     Get R,T,Z from Formed Coil - the previous Formed Coil   
      sc_faname = sc_name + "_formed_rezoned_align"
      r,theta,z=cal_rtz(cnt_beam_start,sc_faname)    
      f_r,f_t,f_z=export_rtz_file(r,theta,z,sc_faname)  
#  Get R,T,Z from Set Coil - the previous After Setting
      sc_saname = sc_name + "_set_rezoned_align"
      r,theta,z=cal_rtz(cnt_beam_start,sc_saname)
      as_r,as_t,as_z=export_rtz_file(r,theta,z,sc_saname)
      #for k in range(0,len(as_r)) :
      #  print "dddd",as_r[k],as_t[k],as_z[k]  
#
# Compare and cal Error between Formed and After Setting
#   
      er,et,ez=cal_Err(as_r,as_t,as_z,f_r,f_t,f_z,'formed')
      err_fname=sc_name+"_form_set_error.csv"
      header=sc_name+" %d-th Error Data(Formed-Set)" %i_no
      write_err_data(err_fname,header,ez,er,et) 

#
# Compare and cal Error between Design and After Setting
#   
      r0,t0,z0 = design_data[3],design_data[4],design_data[2] 
      er,et,ez=cal_Err(as_r,as_t,as_z,r0,t0,z0,'design')
      err_fname=sc_name+"_error.csv"
      header=sc_name+" %d-th Error Data(Design-Set)" %i_no
      write_err_data(err_fname,header,ez,er,et) 
      max_err_z,min_err_z,max_err_r,min_err_r=max(ez),min(ez),max(er),min(er)
      sl = "\n Radius  , Z  \n"
      lf.write(sl)  
      for k in range(0,len(ez)) :
         sl= str(er[k])+" , "+str(ez[k])+",  \n"
         lf.write(sl)
      sl = "Max. Error of Z & Radius  "+str(max_err_z)+" , "+str(min_err_z)+" , "+str(max_err_r)+" , "+str(min_err_r)+" \n"
      print sl
      lf.write(sl)
      i_no=i_no+1
   lf.close()
   return     
   
if __name__ == '__main__':
    py_connect("",40007)
    main()
    py_disconnect()
