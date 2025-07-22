import { Button } from "@/components/ui/button";
import Image from "next/image";

export default function Home() {
  return (
    <div className="flex flex-col items-center justify-center gap-4 h-screen">
      <h1 className="text-4xl font-bold">Hello world</h1>
      <Button variant="default" className="cursor-pointer">Click me</Button>
      <Button variant="outline">Click me</Button>
      <Button variant="destructive">Click me</Button>
      <Button variant="secondary">Click me</Button>
      <Button variant="ghost">Click me</Button>
      <Button variant="link">Click me</Button>
    </div>
  );
}
