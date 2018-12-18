#include <linux/module.h>
#include <linux/init.h>
#include <linux/kernel.h>
#include <linux/miscdevice.h>
#include <linux/fs.h>
#include <linux/ioport.h>
#include <linux/kdev_t.h>
#include <linux/miscdevice.h>
#include <linux/delay.h>
#include <asm/uaccess.h>
#include <asm/ioctl.h>
#include <asm/io.h>

#define DRIVER_AUTHOR 	"CAUCSE HJ"
#define DRIVER_DESC	"driver for chess on FPGA"

#define DMAT_NAME	"chess"
#define DMAT_MODULE_VERSION	"chess V1.0"
#define DMAT_ADDR	0x08000210
#define DMAT_REG_SIZE	0x02
#define NUM_SCANLINES 	10

static unsigned char dmat_fontmap_clear[NUM_SCANLINES] = {
	0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00
};

static unsigned char dmat_fontmap_king[10] = {0x79, 0x09, 0x79, 0x09, 0x09, 0x00, 0x06, 0x09, 0x09, 0x06}; // king

static unsigned char dmat_fontmap_queen[10] = {0x79, 0x09, 0x79, 0x09, 0x09, 0x7D, 0x11, 0x51, 0x40, 0x7F}; // queen

static unsigned char dmat_fontmap_bishop[2][10] = {
	{0x49, 0x49, 0x49, 0x49, 0x49, 0x79, 0x49, 0x49, 0x49, 0x79}, // bi
	{0x08, 0x14, 0x22, 0x14, 0x14, 0x7F, 0x22, 0x3E, 0x22, 0x3E} // shop
};

static unsigned char dmat_fontmap_knight[3][10] = {
	{0x44, 0x44, 0x44, 0x44, 0x47, 0x44, 0x44, 0x44, 0x44, 0x74}, // kn
	{0x11, 0x29, 0x45, 0x45, 0x45, 0x45, 0x45, 0x45, 0x29, 0x11}, // igh
	{0x3F, 0x20, 0x20, 0x3F, 0x20, 0x20, 0x3F, 0x00, 0x00, 0x3F} // t
};

static unsigned char dmat_fontmap_rook[10] = {0x7F, 0x01, 0x7F, 0x40, 0x7F, 0x00, 0x7F, 0x08, 0x7F, 0x01}; // rook

static unsigned char dmat_fontmap_pawn[10] = {0x7F, 0x22, 0x22, 0x7F, 0x08, 0x7F, 0x20, 0x20, 0x20, 0x3E}; // pawn


static unsigned short *dmat_ioremap = NULL;

int dmat_open(struct inode *inodep, struct file *filep)
{
	int alloc_size;
	struct resource *reg;

	alloc_size = DMAT_REG_SIZE * NUM_SCANLINES;

	reg = request_mem_region((unsigned long) DMAT_ADDR, alloc_size, DMAT_NAME);
	if(reg == NULL){
		printk(KERN_ERR "Failed to get 0x%x\n", (unsigned int) DMAT_ADDR);
		return -EBUSY;
	}
	dmat_ioremap = ioremap(DMAT_ADDR, alloc_size);

	return 0;
}

int dmat_release(struct inode *inodep, struct file *filep)
{
	int alloc_size;

	if(!dmat_ioremap){
		return 0;
	}

	alloc_size = DMAT_REG_SIZE * NUM_SCANLINES;

	iounmap(dmat_ioremap);
	release_mem_region((unsigned long) DMAT_ADDR, alloc_size);

	return 0;
}

static void __dmat_write_from_int(unsigned char *data)
{
        int i;
	unsigned short out;

	for(i=0;i<NUM_SCANLINES;i++){
		out=0x007F & data[i];
		iowrite16(out, (dmat_ioremap + i));
	}



}

static void __dmat_write_from_int_name(int num)
{
	int i, j, k;
	unsigned short out;
	unsigned short mid[10];
	unsigned char *data[2];


	for(i=0;i<10;i++){
		
		mid[i] = 0x0000;
			
	}

	for(i=0;i<2;i++){
		data[i] = dmat_fontmap_bishop[i];
	}

	for(i=0;i<2;i++){ //initial
		for(j=0;j<7;j++){  //column	
			for(k=0;k<10;k++){ // row
				mid[k] = mid[k]<<1;
				out = 0x0040 & (data[i][k] << j);
				if(out == 0x0040){
					mid[k] = mid[k]+1;				
				}
				mid[k] = 0x007F & mid[k];
				iowrite16(mid[k], (dmat_ioremap + k));
			}
			msleep(200);			
		}

	}


}

static void __dmat_write_from_int_name_long(int num)
{
	int i, j, k;
	unsigned short out;
	unsigned short mid[10];
	unsigned char *data[3];


	for(i=0;i<10;i++){
		
		mid[i] = 0x0000;
			
	}

	for(i=0;i<3;i++){
		data[i] = dmat_fontmap_knight[i];
	}

	for(i=0;i<3;i++){ //initial
		for(j=0;j<7;j++){  //column	
			for(k=0;k<10;k++){ // row
				mid[k] = mid[k]<<1;
				out = 0x0040 & (data[i][k] << j);
				if(out == 0x0040){
					mid[k] = mid[k]+1;				
				}
				mid[k] = 0x007F & mid[k];
				iowrite16(mid[k], (dmat_ioremap + k));
			}
			msleep(200);			
		}

	}
	
}


ssize_t dmat_write_from_int(struct file *filep, const char *data, size_t length, loff_t *off_what)
{
	int num, ret;

	ret = copy_from_user(&num, data, sizeof(int));
	if(ret < 0){
		printk(KERN_ERR "data copy from userspace failed \n");
		return -EFAULT;
	}

	switch(num){
	case 0:
		__dmat_write_from_int(dmat_fontmap_king);
		msleep(3000);
		__dmat_write_from_int(dmat_fontmap_clear);
		break;
	case 1:
		__dmat_write_from_int(dmat_fontmap_queen);
		msleep(3000);
		__dmat_write_from_int(dmat_fontmap_clear);
		break;
	case 2:
		__dmat_write_from_int_name(2);
		msleep(3000);
		__dmat_write_from_int(dmat_fontmap_clear);
		break;
	case 3:
		__dmat_write_from_int_name_long(3);
		msleep(3000);
		__dmat_write_from_int(dmat_fontmap_clear);
		break;	
	case 4:
		__dmat_write_from_int(dmat_fontmap_rook);
		msleep(3000);
		__dmat_write_from_int(dmat_fontmap_clear);
		break;
	case 5:
		__dmat_write_from_int(dmat_fontmap_pawn);
		msleep(3000);
		__dmat_write_from_int(dmat_fontmap_clear);
		break;
	default:
		__dmat_write_from_int(dmat_fontmap_clear);	
	}

	return length;
}

static struct file_operations dmat_fops = {
	.owner	= THIS_MODULE,
	.open	= dmat_open,
	.write	= dmat_write_from_int,
	.release= dmat_release,
};

static struct miscdevice dmat_driver = {
	.fops	= &dmat_fops,
	.name 	= DMAT_NAME,
	.minor	= MISC_DYNAMIC_MINOR,
};

int dmat_init(void)
{
	misc_register(&dmat_driver);
	printk(KERN_INFO "driver: %s driver init\n", DMAT_NAME);

	return 0;
}

void dmat_exit(void)
{
	misc_deregister(&dmat_driver);
	printk(KERN_INFO "driver: %s driver exit\n", DMAT_NAME);
}

module_init(dmat_init);
module_exit(dmat_exit);

MODULE_AUTHOR(DRIVER_AUTHOR);
MODULE_DESCRIPTION(DRIVER_DESC);
MODULE_LICENSE("Dual BSD/GPL");

