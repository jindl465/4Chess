#include <linux/init.h>
#include <linux/module.h>
#include <linux/kernel.h>
#include <linux/uaccess.h>
#include <asm/uaccess.h>
#include <linux/fs.h>
#include <asm/io.h>
#include <linux/ioport.h>
#include <linux/kdev_t.h>
#include <linux/miscdevice.h>
#include <linux/platform_device.h>
#include <asm/ioctl.h>
#include <linux/delay.h>

#define DRIVER_AUTHOR "CAUCSE KYOUNGCHAN KIM"
#define DRIVER_DESC "driver for Push Swithchs FPGA"

#define SWITCH_NAME "switch"
#define SWITCH_MODULE_VERSION "SWITCH v1.0"
#define SWITCH_ADDR 0x08000050
#define SWITCH_REG_SIZE 0x12

static unsigned short *switch_ioremap = NULL;

int switch_open(struct inode *inodep, struct file *filep){
	struct resource *reg;

	reg = request_mem_region((unsigned long) SWITCH_ADDR, SWITCH_REG_SIZE, SWITCH_NAME);
	if (reg == NULL){
		printk(KERN_WARNING "Cannot get 0x%x\n", (unsigned int) SWITCH_ADDR);
		return -EBUSY;
	}
	switch_ioremap = ioremap(SWITCH_ADDR, SWITCH_REG_SIZE);

	return 0;
}

int switch_release(struct inode *inodep, struct file *filep){
	if (!switch_ioremap){
		return 0;
	}
	iounmap(switch_ioremap);
	release_mem_region((unsigned long) SWITCH_ADDR, SWITCH_REG_SIZE);

	return 0;
}

ssize_t switch_read_byte(struct file *inodep, int* data, size_t length, loff_t *off_what){
	int in = -1, i;
	int result = 0;

	while (result == 0){
		for (i = 0; i < 9; ++i){
			in = ioread16(switch_ioremap +i);
			if (in == 1)
				result++;
			result *= 10;
		}
		result /= 10;
	}
	msleep(350);
	put_user(result, data);

	return length;
}

static struct file_operations switch_fops = {
	.owner = THIS_MODULE,
	.open = switch_open,
	.read = switch_read_byte,
	.release = switch_release,
};

static struct miscdevice switch_driver = {
	.fops = &switch_fops,
	.name = SWITCH_NAME,
	.minor = MISC_DYNAMIC_MINOR,
};

int switch_init(void){
	misc_register(&switch_driver);
	printk(KERN_INFO "driver: %s driver init\n", SWITCH_NAME);
	return 0;
}

void switch_exit(void){
	misc_deregister(&switch_driver);
	printk(KERN_INFO "driver: %s driver exit\n", SWITCH_NAME);
}

module_init(switch_init);
module_exit(switch_exit);

MODULE_AUTHOR(DRIVER_AUTHOR);
MODULE_DESCRIPTION(DRIVER_DESC);
MODULE_LICENSE("Dual BSD/GPL");

